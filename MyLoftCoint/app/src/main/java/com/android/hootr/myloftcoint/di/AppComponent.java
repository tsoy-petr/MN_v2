package com.android.hootr.myloftcoint.di;

import com.android.hootr.myloftcoint.di.module.AppModule;
import com.android.hootr.myloftcoint.screens.main.rate.RateAdapter;
import com.android.hootr.myloftcoint.screens.main.rate.RateFragment;
import com.android.hootr.myloftcoint.screens.start.StartActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

//    Prefs getPrefs();
//
//    void injectMainActivity(MainActivity mainActivity);
//    void injectLaunchActivity(LaunchActivity launchActivity);
//

    void injectStartActivity(StartActivity startActivity);
    void injectRateFragment(RateFragment rateFragment);


    void injectRateAdapter(RateAdapter rateAdapter);


}
