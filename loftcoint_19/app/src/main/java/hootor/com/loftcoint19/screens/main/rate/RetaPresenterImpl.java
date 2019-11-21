package hootor.com.loftcoint19.screens.main.rate;

import android.support.annotation.Nullable;

import java.util.List;

import hootor.com.loftcoint19.data.api.Api;
import hootor.com.loftcoint19.data.db.DataBase;
import hootor.com.loftcoint19.data.db.model.CoinEntity;
import hootor.com.loftcoint19.data.db.model.CoinEntityMapper;
import hootor.com.loftcoint19.data.prefs.Prefs;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RetaPresenterImpl implements RatePresenter {

    private Api api;

    private Prefs prefs;

    private CoinEntityMapper mapper;

    private DataBase dataBase;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Nullable
    private RateView view;


    public RetaPresenterImpl(Api api, Prefs prefs, DataBase dataBase, CoinEntityMapper mapper) {
        this.api = api;
        this.prefs = prefs;
        this.dataBase = dataBase;
        this.mapper = mapper;
    }


    @Override
    public void attachView(RateView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        this.view = null;
    }

    @Override
    public void gateRate() {
        getRate();
    }

    private void getRate() {


        Disposable disposable = dataBase.getCoins()
                .observeOn(AndroidSchedulers.mainThread())
                .replay(1)
                .autoConnect()
                .subscribe(coinEntity -> {
                            if (view != null) {
                                view.setCoins(coinEntity);
                            }
                        }
                        , throwable -> {
                        });

        compositeDisposable.add(disposable);

//        if (view != null) {
//            List<CoinEntity> coinEntities = dataBase.getCoins();
//            view.setCoins(coinEntities);
//        }
    }

    public void loadRate() {

        Disposable disposable = api.ticker()
                .subscribeOn(Schedulers.io())
                .map(rateResponse -> rateResponse.data)
                .map(rete -> mapper.mapCoins(rete))
                .flatMap((Function<List<CoinEntity>, ObservableSource<Boolean>>) coinEntities -> {
                    dataBase.saveCoins(coinEntities);
                    return Observable.just(true);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .replay(1)
                .autoConnect()
                .subscribe(response -> {
                    if (view != null) {
                        if (view != null) {
                            view.setRefreshing(false);
                        }
                    }
                }, throwable -> view.setRefreshing(false));

//        Остановился: RxJava, 118:27

//        api.ticker().enqueue(new Callback<RateResponse>() {
//            @Override
//            public void onResponse(Call<RateResponse> call, Response<RateResponse> response) {
//                if (response.body() != null) {
//
//                    List<Coin> coins = response.body().data;
//                    List<CoinEntity> entities = mapper.mapCoins(coins);
//                    dataBase.saveCoins(entities);
//
//                    if (view != null) {
//                        view.setCoins(entities);
//                    }
//
//                }
//
//                if (view != null) {
//                    view.setRefreshing(false);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RateResponse> call, Throwable t) {
//                if (view != null) {
//                    view.setRefreshing(false);
//                }
//            }
//        });


    }

    @Override
    public void onRefresh() {
        loadRate();
    }
}
