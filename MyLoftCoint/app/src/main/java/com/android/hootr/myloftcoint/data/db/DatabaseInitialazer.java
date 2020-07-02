package com.android.hootr.myloftcoint.data.db;

import android.content.Context;

import androidx.room.Room;

import com.android.hootr.myloftcoint.data.db.room.AppDatabase;
import com.android.hootr.myloftcoint.data.db.room.DatabaseImpRoom;

public class DatabaseInitialazer {

    public Database init(Context context) {
        AppDatabase appDatabase = Room.databaseBuilder(context, AppDatabase.class, "loftcoin.db")
                .fallbackToDestructiveMigration() //Что бы не делать миграцию, при изменении версии база перезатирается
//                .allowMainThreadQueries() //разрешеат работать с БД в mainThread
                .build();
        return new DatabaseImpRoom(appDatabase);
    }
}
