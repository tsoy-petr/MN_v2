package com.android.hootr.myloftcoint.screens.welcom;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.hootr.myloftcoint.R;

import java.util.ArrayList;
import java.util.List;

public class WelcomePagerAdapter extends FragmentPagerAdapter {


    private List<WelcomePage> pages;

    public WelcomePagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        pages = new ArrayList<>();
        pages.add(new WelcomePage(R.drawable.image_welcome_page_1, R.string.welcome_title_1, R.string.welcome_subtitle_1));
        pages.add(new WelcomePage(R.drawable.image_welcome_page_2, R.string.welcome_title_2, R.string.welcome_subtitle_2));
        pages.add(new WelcomePage(R.drawable.image_welcome_page_3, R.string.welcome_title_3, R.string.welcome_subtitle_3));
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return WelcomFragment.getInstance(pages.get(position));
    }

    @Override
    public int getCount() {
        return pages.size();
    }
}
