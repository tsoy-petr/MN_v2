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
import com.android.hootr.myloftcoint.data.db.model.CoinEntity;
import com.android.hootr.myloftcoint.data.model.Fiat;
import com.android.hootr.myloftcoint.data.prefs.Prefs;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RateFragment extends Fragment implements RateView, Toolbar.OnMenuItemClickListener, CurrencyDialog.CurrencyDialogListener {

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

    @Inject
    public RatePresenter presenter;

    public RateAdapter adapter;
    private Unbinder unbinder;

    private Parcelable layoutMangerState;

    private long currentPostion = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = getActivity();

        if (activity == null) {
            return;
        }

//        Api api = ((App) getActivity().getApplication()).getApi();
        Prefs prefs = ((App) getActivity().getApplication()).getPrefs();
//        Database dataBase = ((App) getActivity().getApplication()).getDatabase();
//        CoinEntityMapper mapper = new CoinEntityMapper();
//
//        presenter = new RatePresenterImpl(api, prefs, dataBase, mapper);


        App.getComponent().injectRateFragment(this);

        adapter = new RateAdapter();

        adapter.setHasStableIds(true); // в случае когда id в БД с автогенерацией

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

        Fragment fragment = getFragmentManager().findFragmentByTag(CurrencyDialog.TAG);

        if (fragment != null) {
            ((CurrencyDialog) fragment).setListener(this);
        }

        if (savedInstanceState != null) {
            layoutMangerState = savedInstanceState.getParcelable(LAYOUT_MANAGER_STATE);
        }

        presenter.attachView(this);
        presenter.getRate();

        recycler.getLayoutManager().onRestoreInstanceState(layoutMangerState);

    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        presenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void onStop() {
        presenter.onStopView();
        super.onStop();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_currency:
                presenter.onMenuItemCurrencyClick();
                return true;
        }
        return false;
    }

    @Override
    public void setRefreshing(Boolean refreshing) {
        refresh.setRefreshing(refreshing);
    }

    @Override
    public void showCurrencyDialog() {
        CurrencyDialog dialog = new CurrencyDialog();
        dialog.setListener(this);
        dialog.show(getFragmentManager(), CurrencyDialog.TAG);
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
    public void setCurrencyImage(int resours) {

        MenuItem menuItem = toolbar.getMenu().findItem(R.id.menu_item_currency);
        menuItem.setIcon(resours);

    }

    @Override
    public void setCoins(List<CoinEntity> coins) {
        adapter.setCoins(coins);

        if (layoutMangerState != null) {
            recycler.getLayoutManager().onRestoreInstanceState(layoutMangerState);
            layoutMangerState = null;
        }
    }

    @Override
    public void onCurrencySelected(Fiat currency) {
        presenter.onFiatCurrencySelected(currency);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(LAYOUT_MANAGER_STATE, recycler.getLayoutManager().onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }


}
