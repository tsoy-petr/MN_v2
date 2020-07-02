package com.android.hootr.test.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.hootr.test.R;

public class PrefsImpl implements Prefs {

    private static final String PREFS_NAME = "prefs";
    private static final String KEY_ISLOGIN = "isLogin";
    private static final String KEY_ADRESS_1C_SERVER = "Adress1CServer";
    private static final String KEY_ADRESS_MONGO_SERVER = "AdressMongoServer";

    private Context context;

    public PrefsImpl(Context context) {
        this.context = context;
    }

    private SharedPreferences getPrefs() {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean isLogin() {
        return getPrefs().getBoolean(
                context.getString(R.string.KEY_ISLOGIN)
                , false);
    }

    @Override
    public String getAdress1CServer() {
        return getPrefs().getString(
                context.getString(R.string.KEY_ADRESS_1C_SERVER), "");
    }

    @Override
    public String getAdressMongoServer() {
        return getPrefs().getString(
                context.getString(R.string.KEY_ADRESS_MONGO_SERVER), "");
    }

    @Override
    public void setAdress1CServer(String adress1CServer) {
        getPrefs().edit().putString(context.getString(R.string.KEY_ADRESS_1C_SERVER), adress1CServer).apply();
    }

    @Override
    public void setAdressMongoServer(String adressMongoServer) {
        getPrefs().edit().putString(context.getString(R.string.KEY_ADRESS_MONGO_SERVER), adressMongoServer).apply();
    }

    @Override
    public void setIsLogin(boolean isLogin) {
        getPrefs().edit().putBoolean(context.getString(R.string.KEY_ISLOGIN), isLogin).apply();
    }
}
