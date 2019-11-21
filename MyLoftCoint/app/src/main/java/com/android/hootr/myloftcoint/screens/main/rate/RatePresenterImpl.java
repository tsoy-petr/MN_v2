package com.android.hootr.myloftcoint.screens.main.rate;

import androidx.annotation.Nullable;

import com.android.hootr.myloftcoint.data.api.Api;
import com.android.hootr.myloftcoint.data.api.model.RateResponse;
import com.android.hootr.myloftcoint.data.model.Fiat;
import com.android.hootr.myloftcoint.data.prefs.Prefs;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatePresenterImpl implements RatePresenter {

    private Api api;
    private Prefs prefs;

    private CompositeDisposable disposables = new CompositeDisposable();

    @Nullable
    private RateView view;

    public RatePresenterImpl(Api api, Prefs prefs) {
        this.api = api;
        this.prefs = prefs;
    }

    @Override
    public void attachView(RateView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        disposables.dispose();
        this.view = null;
    }

    @Override
    public void getRate() {

        api.ticker("array", "USD").enqueue(new Callback<RateResponse>() {
            @Override
            public void onResponse(Call<RateResponse> call, Response<RateResponse> response) {
                if (view != null && response.body() != null) {
                    view.setCoins(response.body().data);
                    view.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<RateResponse> call, Throwable t) {
                if (view != null) {
                    view.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        getRate();
    }

    @Override
    public void onMenuItemCurrencyClick() {

    }

    @Override
    public void onFiatCurrencySelected(Fiat currency) {

    }
}
