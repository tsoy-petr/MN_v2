package com.android.hootr.myloftcoint.data.db.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.android.hootr.myloftcoint.data.db.model.CoinEntity;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CoinDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCoins(List<CoinEntity> coins);

    @Query("select * from coin")
    Flowable<List<CoinEntity>> getCoins();

    @Query("SELECT * FROM Coin WHERE symbol = :symbol")
    CoinEntity getCoin(String symbol);

}
