package com.android.hootr.myloftcoint.screens.main.rate;

import android.util.Log;

import androidx.annotation.Nullable;

import com.android.hootr.myloftcoint.R;
import com.android.hootr.myloftcoint.data.api.Api;
import com.android.hootr.myloftcoint.data.db.Database;
import com.android.hootr.myloftcoint.data.db.model.CoinEntityMapper;
import com.android.hootr.myloftcoint.data.model.Fiat;
import com.android.hootr.myloftcoint.data.prefs.Prefs;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RatePresenterImpl implements RatePresenter {

    private Api api;
    private Prefs prefs;
    private Database database;
    private CoinEntityMapper mapper;

    private CompositeDisposable disposables;

    @Nullable
    private RateView view;

    private boolean isLoadData;
    private boolean fromRefresh;

    public RatePresenterImpl(Api api, Prefs prefs, Database dataBase, CoinEntityMapper mapper) {
        this.api = api;
        this.prefs = prefs;
        this.database = dataBase;
        this.mapper = mapper;
        Log.i("happy", "RatePresenterImpl: constructor");
    }

    @Override
    public void attachView(RateView view) {
        this.view = view;
        disposables = new CompositeDisposable();

        Log.i("happy", "fromRefresh: "+ fromRefresh);
        Log.i("happy", "isLoadData: "+ isLoadData);

        if (isLoadData || fromRefresh) {

            if (fromRefresh) {
                view.setRefreshing(true);
            } else {
                Log.i("happy", "isLoadData: ->>>>>>");
                view.showProgress();
            }
        }

    }

    @Override
    public void detachView() {
        disposables.dispose();
        this.view = null;
        Log.i("happy", "detachView: ");
    }

    @Override
    public void onStopView() {
        disposables.clear();
    }

    @Override
    public void getRate() {

        Log.i("happy", "getRate()");

        disposables.add(
                database.getCoins()
                        .observeOn(AndroidSchedulers.mainThread())
                        .replay(1)
                        .autoConnect(1)
                        .subscribe(coinEntities -> {
                            Log.i("happy", "1read from database. view" + view);
                            if (view != null) {
                                Log.i("happy", "2read from database. ");
                                view.setCoins(coinEntities);
                                if (isLoadData) {

                                        view.setRefreshing(false);

                                        view.hideProgress();

                                }
                                isLoadData = false;

                                fromRefresh = false;
                            }
                        })
        );

        loadRate(false);
    }

    private void loadRate(Boolean fromRefresh) {

        this.fromRefresh = fromRefresh;

        isLoadData = true;

        Log.i("happy", "loadRate: ");

        if (!fromRefresh) {
            if (view != null) {
                view.showProgress();
            }
        }

        disposables.add(
                api.ticker("array", prefs.getFiatCurrency().name())
                        .subscribeOn(Schedulers.io())
                        .map(rateResponse -> mapper.mapCoins(rateResponse.data))
                        .map(coinEntities -> {
                            Log.d("happy", "get data from aip and saved in database ");
                            database.saveCoins(coinEntities);
                            return new Object();
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .replay(1)
                        .autoConnect(1)
                        .subscribe(obj -> {
//                            if (fromRefresh) {
//                                view.setRefreshing(false);
//                            } else {
//                                view.hideProgress();
//                            }
                            Log.i("happy", "succes download data from api: ");
                        }, throwable -> {
                            Log.i("happy", "error download data from api: ");


                                view.setRefreshing(false);

                                view.hideProgress();

                        })
        );

    }

    @Override
    public void onRefresh() {
        loadRate(true);
    }

    @Override
    public void onMenuItemCurrencyClick() {

        view.showCurrencyDialog();

    }

    @Override
    public void onFiatCurrencySelected(Fiat currency) {
        prefs.setFiatCurrency(currency);
        loadRate(false);

        if (view != null) {

            switch (currency) {
                case EUR:
                    view.setCurrencyImage(R.drawable.currency_eur);
                    break;
                case USD:
                    view.setCurrencyImage(R.drawable.ic_menu_currency);
                    break;
                case RUB:
                    view.setCurrencyImage(R.drawable.currency_rub);
                    break;
            }


        }
    }
}
