package com.android.hootr.oneactivitymanyfragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    Button button;

    private Listner listner;

    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    public void setListner(Listner listner) {
        this.listner = listner;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button = view.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            if (listner != null) {
                listner.showSearchFragment();
            }
        });

        RxBus.getInstance().publish(101);
//        TextInputEditText inputDataText = view.findViewById(R.id.textInputTextData);
//        inputDataText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                RxBus.getInstance().publish(charSequence.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//            }
//        });
    }

    public interface Listner {
        void showSearchFragment();
    }
}
