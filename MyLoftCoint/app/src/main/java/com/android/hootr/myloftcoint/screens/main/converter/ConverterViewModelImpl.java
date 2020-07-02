package com.android.hootr.myloftcoint.screens.main.converter;

import android.os.Bundle;

import com.android.hootr.myloftcoint.data.db.Database;
import com.android.hootr.myloftcoint.data.db.model.CoinEntity;
import com.android.hootr.myloftcoint.utils.CurrencyFormatter;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class ConverterViewModelImpl implements ConverterViewModel {

    private static final String KEY_SOURCE_CURRENCY = "source_currency";
    private static final String KEY_DESTINATION_CURRENCY = "destination_currency";

    private BehaviorSubject<String> sourceCurrency = BehaviorSubject.create();
    private BehaviorSubject<String> destinationCurrency = BehaviorSubject.create();
    private BehaviorSubject<String> destinationAmount = BehaviorSubject.create();

    private PublishSubject<Object> selectSourceCurrency = PublishSubject.create();
    private PublishSubject<Object> selectDestinationCurrency = PublishSubject.create();

    private String sourceAmountValue = "";

    private CoinEntity sourceCoin;
    private CoinEntity destinationCoin;

    private String sourceCurrencySymbol = "BTC";
    private String destinationCurrencySymbol = "ETH";

    private Database database;

    private CurrencyFormatter currencyFormatter = new CurrencyFormatter();

    private CompositeDisposable disposables = new CompositeDisposable();

    ConverterViewModelImpl(Bundle savedInstanceState, Database database) {

        this.database = database;

//        database.open();

        if (savedInstanceState != null) {
            sourceCurrencySymbol = savedInstanceState.getString(KEY_SOURCE_CURRENCY);
            destinationCurrencySymbol = savedInstanceState.getString(KEY_DESTINATION_CURRENCY);
        }

        loadCoins();
    }

    private void loadCoins() {

        disposables.add(
                Observable.fromCallable(new Callable<CoinEntity>() {
                    @Override
                    public CoinEntity call() throws Exception {
                        return database.getCoin(sourceCurrencySymbol);
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<CoinEntity>() {
                            @Override
                            public void accept(CoinEntity entity) throws Exception {
                                onSourceCurrencySelected(entity);
                            }
                        })
        );

        disposables.add(Observable.fromCallable(new Callable<CoinEntity>() {
            @Override
            public CoinEntity call() throws Exception {
                return database.getCoin(destinationCurrencySymbol);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CoinEntity>() {
                    @Override
                    public void accept(CoinEntity entity) throws Exception {
                        onDestinationCurrencySelected(entity);
                    }
                }));

//        Disposable disposable1 = Observable.fromCallable(() -> database.getCoin(sourceCurrencySymbol))
//                .subscribe(this::onSourceCurrencySelected);
//
//        Disposable disposable2 = Observable.fromCallable(() -> database.getCoin(destinationCurrencySymbol))
//                .subscribe(this::onDestinationCurrencySelected);
//
//        disposables.add(disposable1);
//        disposables.add(disposable2);

    }

    @Override
    public Observable<String> sourceCurrency() {
        return sourceCurrency;
    }

    @Override
    public Observable<String> destinationCurrency() {
        return destinationCurrency;
    }

    @Override
    public Observable<String> destinationAmount() {
        return destinationAmount;
    }

    @Override
    public Observable<Object> selectSourceCurrency() {
        return selectSourceCurrency;
    }

    @Override
    public Observable<Object> selectDestinationCurrency() {
        return selectDestinationCurrency;
    }

    @Override
    public void onSourceAmountChange(String amount) {
        sourceAmountValue = amount;
        convert();
    }

    private void convert() {
        if (sourceAmountValue.isEmpty()) {
            destinationAmount.onNext("");
            return;
        }
        if (sourceCoin == null || destinationCoin == null) {
            return;
        }
        double sourceValue = Double.parseDouble(sourceAmountValue);
        double destinationValue = sourceValue * sourceCoin.usd.price / destinationCoin.usd.price;
        String destinationAmountValue = String.valueOf(currencyFormatter.formatForConverter(destinationValue));
        destinationAmount.onNext(destinationAmountValue);
    }

    @Override
    public void onSourceCurrencyClick() {
        selectSourceCurrency.onNext(new Object());
    }

    @Override
    public void onDestinationCurrencyClick() {
        selectDestinationCurrency.onNext(new Object());
    }

    @Override
    public void onSourceCurrencySelected(CoinEntity coin) {
        sourceCoin = coin;
        sourceCurrencySymbol = coin.symbol;
        sourceCurrency.onNext(coin.symbol);
        convert();
    }

    @Override
    public void onDestinationCurrencySelected(CoinEntity coin) {
        destinationCoin = coin;
        destinationCurrencySymbol = coin.symbol;
        destinationCurrency.onNext(coin.symbol);
        convert();
    }

    @Override
    public void saveState(Bundle outState) {
        outState.putString(KEY_SOURCE_CURRENCY, sourceCurrencySymbol);
        outState.putString(KEY_DESTINATION_CURRENCY, destinationCurrencySymbol);
    }

    @Override
    public void onDetach() {
//        database.close();
    }

}