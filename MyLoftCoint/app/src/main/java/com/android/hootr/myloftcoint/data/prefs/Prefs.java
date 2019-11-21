package com.android.hootr.myloftcoint.data.prefs;

import com.android.hootr.myloftcoint.data.model.Fiat;

public interface Prefs {

    boolean isFirstLaunch();

    void firstLaunch(Boolean firstLaunch);

    void setFirstLaunch(boolean firstLaunch);

    Fiat getFiatCurrency();

    void setFiatCurrency(Fiat currency);
}
