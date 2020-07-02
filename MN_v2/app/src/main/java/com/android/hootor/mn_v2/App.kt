package com.android.hootor.mn_v2

import android.app.Application
import com.android.hootor.mn_v2.presentation.injection.AppComponent
import com.android.hootor.mn_v2.presentation.injection.AppModule
import com.android.hootor.mn_v2.presentation.injection.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context = this))
            .build()
    }

}