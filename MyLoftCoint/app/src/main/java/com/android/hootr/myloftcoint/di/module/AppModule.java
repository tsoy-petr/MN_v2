package com.android.hootr.myloftcoint.di.module;

import com.android.hootr.myloftcoint.data.api.Api;
import com.android.hootr.myloftcoint.data.db.Database;
import com.android.hootr.myloftcoint.data.db.model.CoinEntityMapper;
import com.android.hootr.myloftcoint.data.prefs.Prefs;
import com.android.hootr.myloftcoint.screens.main.rate.RatePresenter;
import com.android.hootr.myloftcoint.screens.main.rate.RatePresenterImpl;
import com.android.hootr.myloftcoint.screens.start.StartPresenter;
import com.android.hootr.myloftcoint.screens.start.StartPresenterImp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {PrefsModule.class, ApiModule.class, DatabaseModule.class})
public class AppModule {

    @Provides
    @Singleton
    public StartPresenter provideStartPresenter(Api api, Prefs prefs, Database dataBase) {
        CoinEntityMapper mapper = new CoinEntityMapper();
        return new StartPresenterImp(api, prefs, dataBase, mapper);
    }

    @Provides
    @Singleton
    public RatePresenter provideRatePresenter(Api api, Prefs prefs, Database dataBase) {
        CoinEntityMapper mapper = new CoinEntityMapper();
        return new RatePresenterImpl(api, prefs, dataBase, mapper);
    }
}
