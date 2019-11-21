package com.android.hootr.myloftcoint.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.hootr.myloftcoint.data.model.Fiat;

public class PrefsImpl implements Prefs {

    private static final String PREFS_NAME = "prefs";
    private static final String KEY_FIRST_LAUNCH = "first_launch";
    private static final String KEY_FIAT_CURRENCY = "fiat_currency";
    private Context context;

    public PrefsImpl(Context context) {
        this.context = context;
    }

    private SharedPreferences getPrefs() {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean isFirstLaunch() {
        return getPrefs().getBoolean(KEY_FIRST_LAUNCH, true);
    }

    @Override
    public void firstLaunch(Boolean firstLaunch) {
        getPrefs().edit().putBoolean(KEY_FIRST_LAUNCH, firstLaunch).apply();
    }

    @Override
    public void setFirstLaunch(boolean firstLaunch) {
        getPrefs().edit().putBoolean(KEY_FIRST_LAUNCH, firstLaunch).apply();
    }

    @Override
    public Fiat getFiatCurrency() {
        return Fiat.valueOf(getPrefs().getString(KEY_FIAT_CURRENCY, Fiat.USD.name()));
    }

    @Override
    public void setFiatCurrency(Fiat currency) {
        getPrefs().edit()
                .putString(KEY_FIAT_CURRENCY, currency.name())
                .apply();
    }
}
