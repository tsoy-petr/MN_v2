package com.android.hootr.gactranslator;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

public class ModelActivity extends AppCompatActivity {

    private Button btn;
    private MyViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

        model = ViewModelProviders.of(this).get(MyViewModel.class);

        LiveData<Long> time = model.getTime();
        time.observe(this, aLong -> {
            model.start();
        });

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(v -> {

        });
    }
}
