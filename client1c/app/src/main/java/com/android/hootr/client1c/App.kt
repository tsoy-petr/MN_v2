package com.android.hootr.client1c

import android.app.Application
import com.android.hootr.client1c.di.AppModule
import com.android.hootr.client1c.di.CacheModule
import com.android.hootr.client1c.di.ViewModelModule
import com.android.hootr.client1c.ui.SettingsFragment
import dagger.Component
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Singleton


class App: Application() {

    companion object {
        lateinit var instance: App
        lateinit var appComponent: AppComponent
    }

    private lateinit var cicerone: Cicerone<Router>

    override fun onCreate() {
        super.onCreate()

        Timber.plant(DebugTree())

        instance = this

        initAppComponent()

        cicerone = Cicerone.create()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()

    }

    fun navigatorHolder() = cicerone.getNavigatorHolder()

    fun router() = cicerone.getRouter()

}

@Singleton
@Component(modules = [AppModule::class, CacheModule::class, ViewModelModule::class])
interface AppComponent {

    //activities
    fun inject(activity: MainActivity)

    fun inject(fragment: SettingsFragment)
}