package hootor.com.loftcoint19.screens.main.rate;


import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hootor.com.loftcoint19.App;
import hootor.com.loftcoint19.R;
import hootor.com.loftcoint19.data.api.Api;
import hootor.com.loftcoint19.data.db.DataBase;
import hootor.com.loftcoint19.data.db.model.CoinEntity;
import hootor.com.loftcoint19.data.db.model.CoinEntityMapper;
import hootor.com.loftcoint19.data.prefs.Prefs;

public class RateFragment extends Fragment implements RateView{

    private static final String LAYOUT_MANAGER_STATE = "layout_manager_state";

    @BindView(R.id.rate_recycler)
    RecyclerView recycler;

    @BindView(R.id.rate_toolbar)
    Toolbar toolbar;

    @BindView(R.id.rate_refresh)
    SwipeRefreshLayout refresh;

    @BindView(R.id.rate_content)
    ViewGroup content;

    @BindView(R.id.progress)
    ViewGroup progress;


    private RatePresenter presenter;
    private RateAdapter adapter;
    private Unbinder unbinder;

    private Parcelable layoutManagerState;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = getActivity();
        if (activity == null) {
            return;
        }

        App app = (App) activity.getApplication();
        Api api = app.getApi();
        Prefs prefs = app.getPrefs();
        DataBase dataBase = app.getDataBase();
        CoinEntityMapper mapper = new CoinEntityMapper();

        presenter = new RetaPresenterImpl(api, prefs, dataBase, mapper);

        adapter = new RateAdapter(prefs);
//        если мы знаем, что у каждого элемента есть уникальный id, то мы переопределяем метод
//        у адаптера getItemId
        adapter.setHasStableIds(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);

        toolbar.setTitle(R.string.rate_screen_title);
//        toolbar.inflateMenu();

        recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh();
            }
        });

        if (savedInstanceState != null) {
            layoutManagerState = savedInstanceState.getParcelable(LAYOUT_MANAGER_STATE);

        }

        presenter.attachView(this);
        presenter.gateRate();
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        presenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(LAYOUT_MANAGER_STATE, recycler.getLayoutManager().onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setCoins(List<CoinEntity> coins) {
        adapter.setCoins(coins);

        if (layoutManagerState != null) {
            recycler.getLayoutManager().onRestoreInstanceState(layoutManagerState);
            layoutManagerState = null;
        }
    }

    @Override
    public void setRefreshing(Boolean refreshing) {
        refresh.setRefreshing(refreshing);
    }

    @Override
    public void ShowCurrencyDialog() {

    }
}
