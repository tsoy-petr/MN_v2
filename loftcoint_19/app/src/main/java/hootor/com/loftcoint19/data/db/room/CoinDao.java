package hootor.com.loftcoint19.data.db.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import hootor.com.loftcoint19.data.db.model.CoinEntity;
import io.reactivex.Flowable;

@Dao
public interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCoins(List<CoinEntity> coins);

    @Query("SELECT * FROM Coin")
    Flowable<List<CoinEntity>> getCoins();
}
