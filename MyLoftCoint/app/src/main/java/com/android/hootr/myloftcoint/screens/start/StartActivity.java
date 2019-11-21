package com.android.hootr.myloftcoint.screens.start;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hootr.myloftcoint.App;
import com.android.hootr.myloftcoint.R;
import com.android.hootr.myloftcoint.data.api.Api;
import com.android.hootr.myloftcoint.data.prefs.Prefs;
import com.android.hootr.myloftcoint.screens.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StartActivity extends AppCompatActivity implements StartView {

    private Unbinder unbinder;

    @BindView(R.id.start_top_corner)
    ImageView topCorner;

    @BindView(R.id.start_bottom_corner)
    ImageView bottomCorner;

    @BindView(R.id.start_outer_circle)
    ImageView outerCircle;

    public static void start(Context context) {
        Intent starter = new Intent(context, StartActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(starter);
    }


    public StartPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        unbinder = ButterKnife.bind(this);

        Api api = ((App) getApplication()).getApi();
        Prefs prefs = ((App) getApplication()).getPrefs();

        presenter = new StartPresenterImp(api, prefs);

//        App.getComponent().injectStartActivity(this);
        presenter.attachView(this);

        presenter.loadRate();
    }

    @Override
    protected void onDestroy() {

        unbinder.unbind();

        if (presenter != null) {
            presenter.detachView();
        }

        super.onDestroy();
    }

    @Override
    public void navigateToMainscreen() {
        MainActivity.start(this);
    }

    private void startAnimations() {

        ObjectAnimator innerAnimator = ObjectAnimator.ofFloat(topCorner, "rotation", 0, 360);
        innerAnimator.setDuration(30000);
        innerAnimator.setRepeatMode(ValueAnimator.RESTART);
        innerAnimator.setRepeatCount(ValueAnimator.INFINITE);
        innerAnimator.setInterpolator(new LinearInterpolator());
        innerAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i("happy", "onAnimationUpdate: " + animation);
            }
        });

        ObjectAnimator outerAnimator = ObjectAnimator.ofFloat(bottomCorner, "rotation", 0, -360);
        outerAnimator.setDuration(55000);
        outerAnimator.setRepeatMode(ValueAnimator.RESTART);
        outerAnimator.setRepeatCount(ValueAnimator.INFINITE);
        outerAnimator.setInterpolator(new LinearInterpolator());

        AnimatorSet set = new AnimatorSet();
        set.play(innerAnimator).with(outerAnimator);
        set.start();
    }

    @Override
    protected void onStart() {
        super.onStart();

        startAnimations();
    }
}
