package hootor.com.loftcoint19.data.db;

import java.util.List;

import hootor.com.loftcoint19.data.db.model.CoinEntity;
import io.reactivex.Flowable;

public interface DataBase {

    void saveCoins(List<CoinEntity> coins);

    Flowable<List<CoinEntity>> getCoins();
}
