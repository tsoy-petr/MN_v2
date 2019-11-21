package hootor.com.loftcoint19.data.prefs;

import hootor.com.loftcoint19.data.model.Fiat;

public interface Prefs {

    boolean isFirstLaunch();

    void setFirstLaunch(Boolean firstLaunch);

    Fiat getFiatCurrency();

    void setFiatCurrency(Fiat currency);
}
