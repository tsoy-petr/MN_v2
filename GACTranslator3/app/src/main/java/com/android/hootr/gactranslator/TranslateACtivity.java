package com.android.hootr.gactranslator;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class TranslateACtivity extends AppCompatActivity implements Observer<TranslateResalt> {

    private EditText source;
    private TextView destination;

    private TranslateViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_activity);

        source = findViewById(R.id.source);
        destination = findViewById(R.id.destination);

        model = ViewModelProviders.of(this).get(TranslateViewModel.class);

    }

    public static final String LANG = "ru-fr";
    public void translate(View view) {
        String sentence = source.getText().toString();
        if (!TextUtils.isEmpty(sentence)) {
            model.translate(sentence, LANG).observe(this, this);
        }
    }

    @Override
    public void onChanged(TranslateResalt translateResalt) {
        if (translateResalt != null) {
            destination.setText(translateResalt.getResult());
        }
    }
}
