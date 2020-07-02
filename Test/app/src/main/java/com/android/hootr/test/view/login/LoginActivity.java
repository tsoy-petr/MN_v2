package com.android.hootr.test.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hootr.test.App;
import com.android.hootr.test.R;
import com.android.hootr.test.data.api.Api1C;
import com.android.hootr.test.data.prefs.Prefs;
import com.android.hootr.test.view.LaunchActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private Button buttonLogin;
    private EditText editLogin;
    private ViewGroup progress;

    private LoginPresenter presenter;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Prefs prefs = ((App) getApplication()).getPrefs();
        Api1C api1C = ((App) getApplication()).getApi1C();

        presenter = new LoginPresenterImpl(prefs, api1C);

        editLogin = findViewById(R.id.edit_cod_user);
        buttonLogin = findViewById(R.id.buttonLogin);
        progress = findViewById(R.id.progress);

        buttonLogin.setOnClickListener(v -> {
            presenter.loginUser(editLogin.getText().toString().trim());
        });

        presenter.attachView(this);

    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToLaunchScreen() {
        LaunchActivity.start(this);
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }
}
