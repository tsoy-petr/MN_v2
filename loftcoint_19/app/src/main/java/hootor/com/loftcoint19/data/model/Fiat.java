package hootor.com.loftcoint19.data.model;

public enum Fiat {

    USD("$"),
    EUR("€"),
    RUB("₽");

    public String symbol;

    Fiat(String symbol) {
        this.symbol = symbol;
    }
}
