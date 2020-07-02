package com.android.hootr.test.view.login;

public interface LoginPresenter {

    void loginUser(String userCode);

    void attachView(LoginView view);

    void detachView();
}
