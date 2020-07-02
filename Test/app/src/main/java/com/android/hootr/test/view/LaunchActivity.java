package com.android.hootr.test.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hootr.test.App;
import com.android.hootr.test.data.prefs.Prefs;
import com.android.hootr.test.view.login.LoginActivity;
import com.android.hootr.test.view.settings.SettingsPrefActivity;
import com.android.hootr.test.view.start.StartActivity;

public class LaunchActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, LaunchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Prefs prefs = ((App)App.getInstance()).getPrefs();

        String adress1CServer = prefs.getAdress1CServer();

        String adressMongoServer = prefs.getAdressMongoServer();

        boolean isLogin = prefs.isLogin();

        if (adress1CServer.isEmpty() || adressMongoServer.isEmpty()) {
            SettingsPrefActivity.start(this);
        } else {
            if(!isLogin) {
                LoginActivity.start(this);
            } else {
                StartActivity.start(this);
                finish();
            }
        }
    }

}
