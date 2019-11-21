package com.android.hootr.myloftcoint.screens.welcom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.hootr.myloftcoint.App;
import com.android.hootr.myloftcoint.R;
import com.android.hootr.myloftcoint.data.prefs.Prefs;
import com.android.hootr.myloftcoint.screens.start.StartActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.pager)
    ViewPager pager;

    @BindView(R.id.start_btn)
    Button startBtn;

//    @BindView(R.id.pageIndicator)
//    PageIndicatorView pageIndicator;

    Unbinder unbinder;

    public static void start(Context context) {
        Intent starter = new Intent(context, WelcomeActivity.class);

        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        unbinder = ButterKnife.bind(this);

        Prefs prefs = ((App)getApplication()).getPrefs();

        pager.setAdapter(new WelcomePagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                pageIndicator.setSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        startBtn.setOnClickListener(v -> {
            prefs.setFirstLaunch(false);
            StartActivity.start(WelcomeActivity.this);
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
