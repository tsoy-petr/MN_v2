package hootor.com.loftcoint19.data.db;

import android.arch.persistence.room.Room;
import android.content.Context;

import hootor.com.loftcoint19.data.db.room.AppDatabase;
import hootor.com.loftcoint19.data.db.room.DataBaseImplRoom;

public class DatabaseInitilazer {

    public DataBase init(Context context) {
        AppDatabase appDatabase
                = Room.databaseBuilder(context, AppDatabase.class, "loftcoin.db")
//                .allowMainThreadQueries() //разрешаем работать с БД на главном потоке
                .fallbackToDestructiveMigration()
                .build();
        return new DataBaseImplRoom(appDatabase);
    }
}
