package com.android.hootr.myloftcoint;

import android.app.Application;

import com.android.hootr.myloftcoint.data.api.Api;
import com.android.hootr.myloftcoint.data.api.ApiInitializer;
import com.android.hootr.myloftcoint.data.prefs.Prefs;
import com.android.hootr.myloftcoint.data.prefs.PrefsImpl;
import com.android.hootr.myloftcoint.di.AppComponent;

public class App extends Application {

    private Api api;
    private Prefs prefs;

    private static App instance;

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        prefs = new PrefsImpl(this);
        api = new ApiInitializer().init();

        instance = this;

//        component = DaggerAppComponent.builder()
//                .contextModule(new ContextModule(this))
//                .prefsModule(new PrefsModule()).
//                        build();


    }

    public Prefs getPrefs() {
        return prefs;
    }

    public Api getApi() {
        return api;
    }

    public static App getInstance() {
        return instance;
    }

    public static AppComponent getComponent() {
        return component;
    }
}
