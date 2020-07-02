package com.android.hootr.test.view.login;

public interface LoginView {

    void showError(String error);

    void navigateToLaunchScreen();

    void showProgress();

    void hideProgress();

}
