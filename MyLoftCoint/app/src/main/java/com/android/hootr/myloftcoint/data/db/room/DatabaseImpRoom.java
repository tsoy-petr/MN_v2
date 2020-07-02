package com.android.hootr.myloftcoint.data.db.room;

import com.android.hootr.myloftcoint.data.db.Database;
import com.android.hootr.myloftcoint.data.db.model.CoinEntity;

import java.util.List;

import io.reactivex.Flowable;

public class DatabaseImpRoom implements Database {


    private AppDatabase database;

    public DatabaseImpRoom(AppDatabase database) {
        this.database = database;
    }

    @Override
    public void saveCoins(List<CoinEntity> coins) {
        database.coinDAO().saveCoins(coins);
    }

    @Override
    public Flowable<List<CoinEntity>> getCoins() {
        return database.coinDAO().getCoins();
    }

    @Override
    public CoinEntity getCoin(String symbol) {
        return database.coinDAO().getCoin(symbol);
    }
}
