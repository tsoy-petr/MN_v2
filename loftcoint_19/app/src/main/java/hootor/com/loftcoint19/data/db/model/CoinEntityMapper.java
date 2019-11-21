package hootor.com.loftcoint19.data.db.model;

import java.util.ArrayList;
import java.util.List;

import hootor.com.loftcoint19.data.api.model.Coin;
import hootor.com.loftcoint19.data.api.model.Quote;
import hootor.com.loftcoint19.data.model.Fiat;

public class CoinEntityMapper {

    public List<CoinEntity> mapCoins(List<Coin> coins) {

        List<CoinEntity> entities = new ArrayList<>();

        for (Coin coin :
                coins) {

            entities.add(mapCoin(coin));
        }

        return entities;
        
    }

    private CoinEntity mapCoin(Coin coin) {

        CoinEntity entity = new CoinEntity();

        entity.id = coin.id;
        entity.name = coin.name;
        entity.symbol = coin.symbol;
        entity.slug = coin.slug;
        entity.rank = coin.rank;
        entity.update = coin.update;

        entity.usd = mapQuote(coin.quote.get(Fiat.USD.name()));
        entity.rub = mapQuote(coin.quote.get(Fiat.RUB.name()));
        entity.eur = mapQuote(coin.quote.get(Fiat.EUR.name()));

        return entity;
    }

    private QuoteEntity mapQuote(Quote quote) {
        if (quote == null) {
            return null;
        }

        QuoteEntity entity = new QuoteEntity();

        entity.price = quote.price;
        entity.percentChange1h = quote.percentChange1h;
        entity.percentChange24h = quote.percentChange24h;
        entity.percentChange7d = quote.percentChange7d;

        return entity;
    }

}
