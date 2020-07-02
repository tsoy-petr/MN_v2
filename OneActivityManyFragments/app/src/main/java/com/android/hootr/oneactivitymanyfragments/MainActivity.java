package com.android.hootr.oneactivitymanyfragments;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity
        implements FirstFragment.Listner, SecondFragment.Listner {

    private FrameLayout fragmentContainer;

    private static final String KEY_CURRENT_FRAGMENT = "currentFragment";
    private int codeCurrentFragment;

    private CompositeDisposable disposables;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_my);

        fragmentContainer = findViewById(R.id.fragment_container);

        if (savedInstanceState != null) {
            codeCurrentFragment = savedInstanceState.getInt(KEY_CURRENT_FRAGMENT, -1);
            Log.i("happy", "onCreate: codeCurrentFragment = " + codeCurrentFragment);

            if (codeCurrentFragment == 102) {
                showSecondFragment();
            } else {
                showFirstFragment();
            }
        } else {
            showFirstFragment();
        }

       RxBus.getInstance().listen()
                .replay(1)
                .autoConnect(1)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer s) {
                        Log.i("happy", "onNext: " + s);
                        codeCurrentFragment = s;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void showFirstFragment() {
        FirstFragment fragment = new FirstFragment();
        fragment.setListner(this);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private void showSecondFragment() {
        SecondFragment fragment = new SecondFragment();
        fragment.setListner(this);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_CURRENT_FRAGMENT, codeCurrentFragment);

        Log.i("happy", "onSaveInstanceState: codeCurrentFragment" + codeCurrentFragment);
    }

    @Override
    public void showSearchFragment() {
        showSecondFragment();
    }

    @Override
    public void searchOrder(String numserOfOrder) {
        showFirstFragment();
    }
}
