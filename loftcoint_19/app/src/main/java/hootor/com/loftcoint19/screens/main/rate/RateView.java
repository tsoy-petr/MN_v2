package hootor.com.loftcoint19.screens.main.rate;

import java.util.List;

import hootor.com.loftcoint19.data.db.model.CoinEntity;

public interface RateView {

    void setCoins(List<CoinEntity> coins);

    void setRefreshing(Boolean refreshing);

    void ShowCurrencyDialog();
}
