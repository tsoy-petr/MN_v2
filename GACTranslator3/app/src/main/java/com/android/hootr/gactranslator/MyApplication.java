package com.android.hootr.gactranslator;

import android.app.Application;

public class MyApplication extends Application {

    private static MyApplication instanse;

    @Override
    public void onCreate() {
        super.onCreate();
        instanse = this;
    }

    public static synchronized MyApplication getInstance() {
        return instanse;
    }
}
