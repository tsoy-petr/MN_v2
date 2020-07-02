package com.android.hootr.test.view.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.android.hootr.test.R;

public class SettingsActivity extends AppCompatActivity {


    public static void start(Context context) {

        Intent starter = new Intent(context, SettingsActivity.class);

//        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        context.startActivity(starter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            NavUtils.getParentActivityIntent(this);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
