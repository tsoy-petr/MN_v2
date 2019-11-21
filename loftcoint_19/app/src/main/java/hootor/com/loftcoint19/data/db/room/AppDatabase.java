package hootor.com.loftcoint19.data.db.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import hootor.com.loftcoint19.data.db.model.CoinEntity;

@Database(entities = {CoinEntity.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CoinDao coinDao();
}
