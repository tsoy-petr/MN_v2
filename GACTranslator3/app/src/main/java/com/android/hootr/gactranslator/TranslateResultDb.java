package com.android.hootr.gactranslator;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TranslateResalt.class}, version = 1)
public abstract class TranslateResultDb extends RoomDatabase {

    public abstract TranslateResultDao translateResultDao();
}
