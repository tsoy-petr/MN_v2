package com.android.hootr.myloftcoint.screens.start;

import androidx.annotation.Nullable;

import com.android.hootr.myloftcoint.data.api.Api;
import com.android.hootr.myloftcoint.data.api.model.Coin;
import com.android.hootr.myloftcoint.data.db.Database;
import com.android.hootr.myloftcoint.data.db.model.CoinEntity;
import com.android.hootr.myloftcoint.data.db.model.CoinEntityMapper;
import com.android.hootr.myloftcoint.data.prefs.Prefs;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class StartPresenterImp implements StartPresenter {

    @Nullable
    private StartView view;

    private Api api;
    private Prefs prefs;
    private Database database;
    private CoinEntityMapper mapper;

    private CompositeDisposable compositeDisposable;

    public StartPresenterImp(Api api, Prefs prefs, Database dataBase, CoinEntityMapper mapper) {
        this.api = api;
        this.prefs = prefs;
        this.database = dataBase;
        this.mapper = mapper;

    }

    @Override
    public void attachView(StartView startView) {
        this.view = startView;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        this.view = null;
    }

    @Override
    public void loadRate() {

        compositeDisposable.add(
                api.ticker("array", prefs.getFiatCurrency().name())
                        .subscribeOn(Schedulers.io())
                        .map(rateResponse -> {
                            List<Coin> coins = rateResponse.data;
                            List<CoinEntity> entities = mapper.mapCoins(coins);
                            database.saveCoins(entities);
                            return entities;
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(coinEntities -> {
                                    if (view != null) {
                                        view.navigateToMainscreen();
                                    }
                                },
                                throwable -> {
                                    if (view != null) {
                                        view.navigateToMainscreen();
                                    }
                                })
        );

//        compositeDisposable.add(api.ticker("array", prefs.getFiatCurrency().name())
//                .subscribeOn(Schedulers.io())
//
//                .map(new Function<RateResponse, List<Coin>>() {
//                    @Override
//                    public List<Coin> apply(RateResponse rateResponse) throws Exception {
//                        return rateResponse.data;
//                    }
//                })
//                .map(new Function<List<Coin>, List<CoinEntity>>() {
//                    @Override
//                    public List<CoinEntity> apply(List<Coin> coins) throws Exception {
//                        return mapper.mapCoins(coins);
//                    }
//                })
//                .flatMap(new Function<List<CoinEntity>, ObservableSource<Boolean>>() {
//                    @Override
//                    public ObservableSource<Boolean> apply(List<CoinEntity> coinEntities) throws Exception {
//                        database.saveCoins(coinEntities);
//                        return Observable.just(true);
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//
//                        if (aBoolean == true && view != null) {
//                            view.navigateToMainscreen();
//                        }
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                    }
//                }));

    }
}
