package com.android.hootr.myloftcoint;

import android.app.Application;

import com.android.hootr.myloftcoint.data.api.Api;
import com.android.hootr.myloftcoint.data.api.ApiInitializer;
import com.android.hootr.myloftcoint.data.db.Database;
import com.android.hootr.myloftcoint.data.db.DatabaseInitialazer;
import com.android.hootr.myloftcoint.data.prefs.Prefs;
import com.android.hootr.myloftcoint.data.prefs.PrefsImpl;
import com.android.hootr.myloftcoint.di.AppComponent;
import com.android.hootr.myloftcoint.di.DaggerAppComponent;

public class App extends Application {

    private Api api;
    private Prefs prefs;

    private static App instance;

    private Database database;

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        prefs = new PrefsImpl(this);
        api = new ApiInitializer().init();

        instance = this;

        database = new DatabaseInitialazer().init(this);

        component = DaggerAppComponent.create();


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

    public Database getDatabase() {
        return database;
    }
}
