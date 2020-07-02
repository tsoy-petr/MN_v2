package com.android.hootr.test.view.ordres;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hootr.test.R;

public class OrdersActivity extends AppCompatActivity implements OrderView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
    }
}
