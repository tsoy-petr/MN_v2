package hootor.com.loftcoint19.data.db.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import hootor.com.loftcoint19.data.model.Fiat;

@Entity(tableName = "Coin")
public class CoinEntity {

    @PrimaryKey
    public int id;

    public String name;

    public String symbol;

    public String slug;

    public int rank;

    public long update;

    @Embedded(prefix = "usd_")
    public QuoteEntity usd;

    @Embedded(prefix = "rub_")
    public QuoteEntity rub;

    @Embedded(prefix = "eur_")
    public QuoteEntity eur;

    public QuoteEntity getQuot(Fiat fiat) {

        QuoteEntity quote = null;

        switch (fiat) {
            case EUR:
                quote = eur;
                        break;
            case RUB:
                quote = rub;
                break;
            case USD:
                quote = usd;
                break;
        }

        if (quote == null) {
            quote = usd;
        }
        return quote;

    }
}
