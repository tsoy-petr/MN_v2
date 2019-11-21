package com.android.hootr.myloftcoint.screens.start;

import androidx.annotation.Nullable;

import com.android.hootr.myloftcoint.data.api.Api;
import com.android.hootr.myloftcoint.data.api.model.RateResponse;
import com.android.hootr.myloftcoint.data.prefs.Prefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartPresenterImp implements StartPresenter {

    @Nullable
    private StartView view;

    private Api api;
    private Prefs prefs;

    public StartPresenterImp(Api api, Prefs prefs) {
        this.api = api;
        this.prefs = prefs;
    }

    @Override
    public void attachView(StartView startView) {
        this.view = startView;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void loadRate() {

        api.ticker("array", "USD").enqueue(new Callback<RateResponse>() {
            @Override
            public void onResponse(Call<RateResponse> call, Response<RateResponse> response) {
                if (view != null) {
                    view.navigateToMainscreen();
                }
            }

            @Override
            public void onFailure(Call<RateResponse> call, Throwable t) {

            }
        });
    }
}
