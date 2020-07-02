package com.android.hootr.myloftcoint.screens.main.rate;

import com.android.hootr.myloftcoint.data.model.Fiat;

public interface RatePresenter {

    void attachView(RateView view);

    void detachView();

    void onStopView();

    void getRate();

    void onRefresh();

    void onMenuItemCurrencyClick();

    void onFiatCurrencySelected(Fiat currency);

}
