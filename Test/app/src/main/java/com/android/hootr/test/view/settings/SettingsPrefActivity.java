package com.android.hootr.test.view.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hootr.test.App;
import com.android.hootr.test.R;
import com.android.hootr.test.data.prefs.Prefs;

public class SettingsPrefActivity extends AppCompatActivity {

    private Prefs prefs;

    private EditText edit_adress_server_1c;
    private EditText edit_adress_mongo_db_server;
    private Button btn_savePrefs;
    private Button btn_cancel_pref;

    public static void start(Context context) {
        Intent starter = new Intent(context, SettingsPrefActivity.class);
//        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(starter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_pref);

        edit_adress_server_1c = findViewById(R.id.edit_adress_server_1c);
        edit_adress_mongo_db_server = findViewById(R.id.edit_adress_mongo_db_server);
        btn_savePrefs = findViewById(R.id.btn_savePrefs);
        btn_savePrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePrefs();
            }
        });
        btn_cancel_pref = findViewById(R.id.btn_cancel_pref);
        btn_cancel_pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        prefs = ((App) App.getInstance()).getPrefs();

        edit_adress_server_1c.setText(prefs.getAdress1CServer());
        edit_adress_mongo_db_server.setText(prefs.getAdressMongoServer());

    }

    private void savePrefs() {
        prefs.setAdress1CServer(edit_adress_server_1c.getText().toString().trim());
        prefs.setAdressMongoServer(edit_adress_mongo_db_server.getText().toString().trim());

        finish();
    }
}
