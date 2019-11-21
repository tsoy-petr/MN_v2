package com.android.hootr.myloftcoint.screens.main.rate;

import com.android.hootr.myloftcoint.data.api.model.Coin;
import com.android.hootr.myloftcoint.data.model.Fiat;

import java.util.List;

public interface RateView {

    void setCoins(List<Coin> coins);

    void setRefreshing(Boolean refreshing);

    void showCurrencyDialog();

    void showProgress();

    void hideProgress();

    void setCurrencyImage(Fiat currency);


}
