package com.android.hootr.myloftcoint.screens.main.rate;

import androidx.annotation.DrawableRes;

import com.android.hootr.myloftcoint.data.db.model.CoinEntity;

import java.util.List;

public interface RateView {

    void setCoins(List<CoinEntity> coins);

    void setRefreshing(Boolean refreshing);

    void showCurrencyDialog();

    void showProgress();

    void hideProgress();

    void setCurrencyImage(@DrawableRes int resours);

}
