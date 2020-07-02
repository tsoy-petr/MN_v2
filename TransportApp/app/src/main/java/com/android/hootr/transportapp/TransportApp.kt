package com.android.hootr.transportapp

import android.app.Application
import com.android.hootr.domen.id.DaggerDomainComponent
import com.android.hootr.domen.id.DomainComponent
import com.android.hootr.presentation.di.DaggerPresentationComponent
import com.android.hootr.presentation.di.PresentationComponent
import com.android.hootr.transportapp.di.DaggerAppComponent
import com.android.hootr.utilities.di.DaggerUtilsComponent
import com.android.hootr.utilities.di.UtilsComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TransportApp: Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector : DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()

        val appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .presentationComponent(providePresentationComponent())
            .utilsComponent(provideUtilsComponent())
            .build()

        appComponent.inject(this)
    }

    private fun providePresentationComponent(): PresentationComponent {
        return DaggerPresentationComponent
            .builder()
            .domainComponent(provideDomainComponent())
            .utilsComponent(provideUtilsComponent())
            .build()
    }

    private fun provideDomainComponent(): DomainComponent {
        return DaggerDomainComponent
            .builder()
            .build()
    }

    private fun provideUtilsComponent(): UtilsComponent {
        return DaggerUtilsComponent
            .builder()
            .application(this)
            .build()
    }
}