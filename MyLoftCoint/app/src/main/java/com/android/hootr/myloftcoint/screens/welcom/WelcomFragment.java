package com.android.hootr.myloftcoint.screens.welcom;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.hootr.myloftcoint.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WelcomFragment extends Fragment {

    public static final String KEY_NAME = "page";

    public static WelcomFragment getInstance(WelcomePage page) {
        Bundle args = new Bundle();
        args.putParcelable(KEY_NAME, page);

        WelcomFragment fragment = new WelcomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public WelcomFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.icon)
    public ImageView icon;
    @BindView(R.id.title)
    public TextView title;
    @BindView(R.id.subtitle)
    public TextView subtitle;

    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcom, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        Bundle args = getArguments();
        if (args != null) {
            WelcomePage page = args.getParcelable(KEY_NAME);
            if (page != null) {
                icon.setImageResource(page.getIcon());
                title.setText(page.getTitle());
                subtitle.setText(page.getSubTitle());
            }
        }
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
