package com.android.hootr.myloftcoint.screens.main.rate;


import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.hootr.myloftcoint.App;
import com.android.hootr.myloftcoint.R;
import com.android.hootr.myloftcoint.data.api.Api;
import com.android.hootr.myloftcoint.data.api.model.Coin;
import com.android.hootr.myloftcoint.data.model.Fiat;
import com.android.hootr.myloftcoint.data.prefs.Prefs;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RateFragment extends Fragment implements RateView, Toolbar.OnMenuItemClickListener, CurrencyDialog.CurrencyDialogListener{

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

    private Parcelable layoutMangerState;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = getActivity();

        if (activity == null) {
            return;
        }

        Api api = ((App) getActivity().getApplication()).getApi();
        Prefs prefs = ((App) getActivity().getApplication()).getPrefs();

//        Database mainDatabase = ((App) getActivity().getApplication()).getDatabase();
//        Database workerDatabase = ((App) getActivity().getApplication()).getDatabase();
//
//        CoinEntityMapper mapper = new CoinEntityMapper();
//
        presenter = new RatePresenterImpl(api, prefs);

        adapter = new RateAdapter(prefs);

        adapter.setHasStableIds(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_rate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);

        toolbar.setTitle(R.string.rate_screen_title);
        toolbar.inflateMenu(R.menu.menu_rate);
        toolbar.setOnMenuItemClickListener(this);

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
            layoutMangerState = savedInstanceState.getParcelable(LAYOUT_MANAGER_STATE);
        }

        Fragment fragment = getFragmentManager().findFragmentByTag(CurrencyDialog.TAG);

        if (fragment != null) {
            ((CurrencyDialog) fragment).setListener(this);
        }


        presenter.attachView(this);
        presenter.getRate();

    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        presenter.detachView();
        super.onDestroyView();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void setRefreshing(Boolean refreshing) {
        refresh.setRefreshing(refreshing);
    }

    @Override
    public void showCurrencyDialog() {

    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void setCurrencyImage(Fiat currency) {

    }

    @Override
    public void setCoins(List<Coin> coins) {
        adapter.setCoins(coins);
    }

    @Override
    public void onCurrencySelected(Fiat currency) {
        presenter.onFiatCurrencySelected(currency);
    }
}
