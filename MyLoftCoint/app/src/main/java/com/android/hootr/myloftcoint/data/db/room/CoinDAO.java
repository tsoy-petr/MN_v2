package com.android.hootr.myloftcoint.data.db.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.android.hootr.myloftcoint.data.db.model.CoinEntity;

import java.util.List;

@Dao
public interface CoinDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCoins(List<CoinEntity> coins);

    @Query("select * from coin")
    List<CoinEntity> getCoins();
}
