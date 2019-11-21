package hootor.com.loftcoint19.data.db.room;

import java.util.List;

import hootor.com.loftcoint19.data.db.DataBase;
import hootor.com.loftcoint19.data.db.model.CoinEntity;
import io.reactivex.Flowable;

public class DataBaseImplRoom implements DataBase {

    private AppDatabase database;

    public DataBaseImplRoom(AppDatabase database) {
        this.database = database;
    }

    @Override
    public void saveCoins(List<CoinEntity> coins) {
        database.coinDao().saveCoins(coins);
    }

    @Override
    public Flowable<List<CoinEntity>> getCoins() {
        return database.coinDao().getCoins();
    }
}
