package com.android.hootr.testcicerone

import android.app.Application
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class App: Application() {

    private lateinit var cicerone: Cicerone<Router>

    override fun onCreate() {
        super.onCreate()
        instance = this
        cicerone = Cicerone.create()
    }

    fun navigatorHolder() = cicerone.getNavigatorHolder()

    fun router() = cicerone.getRouter()

    companion object {
        lateinit var instance: App
    }

}

class MainRouter() : Router() {

}