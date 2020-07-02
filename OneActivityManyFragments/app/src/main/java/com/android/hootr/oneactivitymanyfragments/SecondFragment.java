package com.android.hootr.oneactivitymanyfragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    private TextView txtName;
    Button button2;

    private Listner listner;

    public static SecondFragment newInstance(Context context) {
        return new SecondFragment();
    }

    public SecondFragment() {
        // Required empty public constructor
    }

    public void setListner(Listner listner) {
        this.listner = listner;
    }

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtName = view.findViewById(R.id.textViewName);

        button2 = view.findViewById(R.id.button2);
        button2.setOnClickListener(v -> {
            if (listner != null) {
                listner.searchOrder("same order");
            }
        });

        //  listen RxJava event here
//        RxBus.getInstance().listen().subscribe(getInputObserver());

        RxBus.getInstance().publish(102);
    }

    // Get input observer instance
    private Observer<String> getInputObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                txtName.setText(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public interface Listner {

        void searchOrder(String numserOfOrder);

    }

}
