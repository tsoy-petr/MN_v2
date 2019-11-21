package hootor.com.loftcoint19.screens.start;

import android.support.annotation.Nullable;

import java.util.List;

import hootor.com.loftcoint19.data.api.Api;
import hootor.com.loftcoint19.data.api.model.Coin;
import hootor.com.loftcoint19.data.db.DataBase;
import hootor.com.loftcoint19.data.db.model.CoinEntity;
import hootor.com.loftcoint19.data.db.model.CoinEntityMapper;
import hootor.com.loftcoint19.data.prefs.Prefs;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class StartPresentrImpl implements StartPresentr {

    private static final String TAG = "StartPresentrImpl";
    @Nullable
    private StartView view;

    private Api api;

    private Prefs prefs;

    private CoinEntityMapper mapper;

    private DataBase dataBase;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public StartPresentrImpl(Api api, Prefs prefs, DataBase dataBase, CoinEntityMapper mapper) {
        this.api = api;
        this.prefs = prefs;
        this.mapper = mapper;
        this.dataBase = dataBase;
    }

    @Override
    public void attachView(StartView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        view = null;
    }

    @Override
    public void loadRate() {

        Disposable disposable = api.ticker()
                .subscribeOn(Schedulers.io())
                .map(rateResponse -> {
                    List<Coin> coins = rateResponse.data;
                    List<CoinEntity> coinEntities = mapper.mapCoins(coins);
                    dataBase.saveCoins(coinEntities);
                    return coinEntities;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(coinEntities -> {
                            if (view != null) {
                                view.navigatToMainActivity();
                            }
                        }
                        , error -> {
                        });
        compositeDisposable.add(disposable);
//        compositeDisposable.add(disposable);
//                .map(coins -> mapper.mapCoins(coins))
//                .flatMap(coinEntities -> {
//                    dataBase.saveCoins(coinEntities);
//                    return Observable.just(true);
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .replay(1)
//                .autoConnect(1)
//                .subscribe(response -> {
//                            if (view != null && response) {
//                                view.navigatToMainActivity();
//                            }
//                        },
//                        throwable -> {
//
//                        }));


//        api.ticker("1", "100", "USD").enqueue(new Callback<RateResponse>() {
//        api.ticker().enqueue(new Callback<RateResponse>() {
//            @Override
//            public void onResponse(Call<RateResponse> call, Response<RateResponse> response) {
//
//                if (response.body() != null) {
//
//                    List<Coin> coins = response.body().data;
//                    List<CoinEntity> entities = mapper.mapCoins(coins);
//
//                    dataBase.saveCoins(entities);
//                }
//
//                if (view != null) {
//                    view.navigatToMainActivity();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RateResponse> call, Throwable t) {
//                Log.e(TAG, "Load rate error", t);
//            }
//        });

    }
}
