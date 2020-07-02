package com.android.hootr.myloftcoint.di.module;

import android.app.Application;

import com.android.hootr.myloftcoint.App;
import com.android.hootr.myloftcoint.data.prefs.Prefs;
import com.android.hootr.myloftcoint.data.prefs.PrefsImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PrefsModule {

    @Provides
    @Singleton
    public Prefs providePrefs() {
        return new PrefsImpl((Application) App.getInstance());
    }
}
