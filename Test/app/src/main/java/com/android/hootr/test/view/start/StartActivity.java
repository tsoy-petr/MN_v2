package com.android.hootr.test.view.start;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hootr.test.R;

public class StartActivity extends AppCompatActivity implements StartView{

    public static void start(Context context) {
        Intent intent = new Intent(context, StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);



    }

    @Override
    public void openOrderOperation() {

    }
}
