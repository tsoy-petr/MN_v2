package com.android.hootr.myloftcoint.screens.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.hootr.myloftcoint.R;
import com.android.hootr.myloftcoint.data.prefs.Prefs;
import com.android.hootr.myloftcoint.screens.main.converter.ConverterFragment;
import com.android.hootr.myloftcoint.screens.main.rate.RateFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(starter);
    }

    private Unbinder unbinder;

    @BindView(R.id.bottom_navigation)
    public BottomNavigationView navigation;

//    @Inject
    public Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        App.getComponent().injectMainActivity(this);

        unbinder = ButterKnife.bind(this);

        navigation.setOnNavigationItemSelectedListener(navigationListener);
        navigation.setOnNavigationItemReselectedListener(item -> {});

        if (savedInstanceState == null) {
            navigation.setSelectedItemId(R.id.menu_item_rate);
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener = item -> {
        switch (item.getItemId()){
            case R.id.menu_item_accounts:
                showWalletsFragment();
                break;
            case R.id.menu_item_rate:
                showRateFragment();
                break;
            case R.id.menu_item_converter:
                showConverterFragment();
                break;
        }

        return true;
    };

    private void showWalletsFragment() {
//        WalletsFragment fragment = new WalletsFragment();
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//        transaction.replace(R.id.fragment_container, fragment);
//        transaction.commit();
    }

    private void showRateFragment() {
        RateFragment fragment = new RateFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private void showConverterFragment() {
        ConverterFragment fragment = new ConverterFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }


    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
}
