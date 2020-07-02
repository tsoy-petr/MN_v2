package com.android.hootr.test.view.login;

import android.util.Log;

import com.android.hootr.test.data.api.Api1C;
import com.android.hootr.test.data.prefs.Prefs;
import com.android.hootr.test.utils.Util;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenterImpl implements LoginPresenter {

    private Prefs prefs;
    private Api1C api1C;

    private CompositeDisposable disposables;

    private LoginView view;

    public LoginPresenterImpl(Prefs prefs, Api1C api1C) {
        this.prefs = prefs;
        this.api1C = api1C;
    }


    @Override
    public void loginUser(String userCode) {

        if (view != null) {
            view.showProgress();
        }

        String idGadget = Util.ID_gadget();

        if (api1C != null) {
            disposables.add(api1C.login(userCode, idGadget)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(loginResultServer -> {
                        Log.i("happy", "loginUser: isError = " + loginResultServer.isError);
                        if (view != null) {
                            view.hideProgress();
                        }
                    }, throwable -> {
                        Log.i("happy", "error: ");
                        if (view != null) {
                            view.hideProgress();
                        }
                    }));
        } else {
            if (view != null) {
                view.showError("Не настроено подключение к серверу");
                view.hideProgress();
            }
        }

    }

    @Override
    public void attachView(LoginView view) {
        this.view = view;
        disposables = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        this.view = null;
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }
}
