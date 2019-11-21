package com.android.hootr.myloftcoint.screens.launch;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hootr.myloftcoint.App;
import com.android.hootr.myloftcoint.data.prefs.Prefs;
import com.android.hootr.myloftcoint.screens.start.StartActivity;
import com.android.hootr.myloftcoint.screens.welcom.WelcomeActivity;



public class LaunchActivity extends AppCompatActivity {


    public Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        App.getComponent().injectLaunchActivity(this);
        Prefs prefs = ((App) getApplication()).getPrefs();

        if (prefs.isFirstLaunch()) {
            WelcomeActivity.start(this);
        } else {
            StartActivity.start(this);
        }

    }
}
