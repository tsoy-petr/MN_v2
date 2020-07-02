package com.android.hootr.tsd

import android.app.Application
import com.android.hootr.tsd.di.ApplicationComponent
import com.android.hootr.tsd.di.DaggerApplicationComponent
import com.android.hootr.tsd.di.module.ApplicationModule

class App : Application(){

       lateinit var applicationComponent: ApplicationComponent

//    , ViewModelStoreOwner
//{

//    val leakedViews = mutableListOf<Activity>()

//    private var prefs: Prefs? = null

//    var vmBD: ViewModelBC? = null
//    private val appViewModelStore: ViewModelStore by lazy {
//        ViewModelStore()
//    }

    companion object {
        var instance: App? = null
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

//        prefs = PrefsImp(this)
//
//       App.instance = this

//       vmBD = ViewModelProvider(this).get(com.android.hootr.tsd.ViewModelBC::class.java)


    }

//    override fun onTerminate() {
//        super.onTerminate()
//    }

//    fun getPrefs() = prefs

//    override fun getViewModelStore(): ViewModelStore {
//        return appViewModelStore
//    }

}