package com.android.hootr.test;

import android.app.Application;

import com.android.hootr.test.data.api.Api1C;
import com.android.hootr.test.data.api.Api1CInitializer;
import com.android.hootr.test.data.prefs.Prefs;
import com.android.hootr.test.data.prefs.PrefsImpl;

public class App extends Application {

    private static Application instance;

    private Prefs prefs;
    private Api1C api1C;

    private Object LOCK;

    @Override
    public void onCreate() {
        super.onCreate();

        LOCK = new Object();

        instance = this;

        prefs = new PrefsImpl(this);

//        api1C = new Api1CInitializer().init(prefs.getAdress1CServer());

    }

    public static Application getInstance() {
        return instance;
    }

    public Prefs getPrefs() {
        return prefs;
    }

    public Api1C getApi1C() {

        synchronized (LOCK) {

            if (api1C == null) {
                if (!prefs.getAdress1CServer().isEmpty()){
                    api1C = new Api1CInitializer().init(prefs.getAdress1CServer());
                }
            }

            return api1C;

//            if (prefs.getAdress1CServer().isEmpty())
//            return api1C;
        }
    }
}
