package com.android.hootr.myloftcoint.data.db;

import com.android.hootr.myloftcoint.data.db.model.CoinEntity;

import java.util.List;

import io.reactivex.Flowable;

public interface Database {

    void saveCoins(List<CoinEntity> coins);

    Flowable<List<CoinEntity>> getCoins();

    CoinEntity getCoin(String symbol);

}
