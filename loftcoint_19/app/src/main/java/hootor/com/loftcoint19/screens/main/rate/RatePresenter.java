package hootor.com.loftcoint19.screens.main.rate;

public interface RatePresenter {

    void attachView(RateView view);
    void detachView();

    void gateRate();

    void onRefresh();
}
