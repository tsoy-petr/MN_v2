package com.android.hootr.myloftcoint.data.db.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.android.hootr.myloftcoint.data.db.model.CoinEntity;

@Database(entities = {CoinEntity.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CoinDAO coinDAO();

}
