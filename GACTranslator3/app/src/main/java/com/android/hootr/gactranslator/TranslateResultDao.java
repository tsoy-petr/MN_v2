package com.android.hootr.gactranslator;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TranslateResultDao {

    @Query("SELECT * FROM result")
    List<TranslateResalt> getAll();

    @Query("SELECT * FROM result WHERE sentence = :sentence AND lang = :lang LIMIT 1")
    LiveData<TranslateResalt> translate(String sentence, String lang);

    @Insert(onConflict = REPLACE)
    void insertAll(TranslateResalt ... resalts);

    @Query("DELETE FROM result")
    void deleteAll();

//    @DELETE
//    void delete(TranslateResalt translateResalt);

    @Query("UPDATE result SET status =:status WHERE lang =:lang")
    void updateAll(String status, String lang);

    @Update
    void updateAll(TranslateResalt ... resalts);
}
