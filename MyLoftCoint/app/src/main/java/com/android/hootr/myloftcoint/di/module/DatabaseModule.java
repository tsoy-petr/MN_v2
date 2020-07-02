package com.android.hootr.myloftcoint.di.module;

import android.app.Application;

import com.android.hootr.myloftcoint.App;
import com.android.hootr.myloftcoint.data.db.Database;
import com.android.hootr.myloftcoint.data.db.DatabaseInitialazer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    Database providesDatabase() {
        return new DatabaseInitialazer().init((Application) App.getInstance());
    }
}
