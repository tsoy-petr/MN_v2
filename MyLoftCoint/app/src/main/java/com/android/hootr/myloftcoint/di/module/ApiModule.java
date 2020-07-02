package com.android.hootr.myloftcoint.di.module;

import com.android.hootr.myloftcoint.data.api.Api;
import com.android.hootr.myloftcoint.data.api.ApiInitializer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {

    @Provides
    @Singleton
    Api providesApi() {
        return new ApiInitializer().init();
    }

}
