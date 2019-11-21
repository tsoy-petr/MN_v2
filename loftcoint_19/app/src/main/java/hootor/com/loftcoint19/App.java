package hootor.com.loftcoint19;

import android.app.Application;

import hootor.com.loftcoint19.data.api.Api;
import hootor.com.loftcoint19.data.api.ApiInitializer;
import hootor.com.loftcoint19.data.db.DataBase;
import hootor.com.loftcoint19.data.db.DatabaseInitilazer;
import hootor.com.loftcoint19.data.prefs.Prefs;
import hootor.com.loftcoint19.data.prefs.PrefsImpl;

public class App extends Application {

    private Prefs prefs;
    private Api api;
    private DataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();

        prefs = new PrefsImpl(this);

        api = new ApiInitializer().init();

        dataBase = new DatabaseInitilazer().init(this);
    }

    public Prefs getPrefs() {
        return prefs;
    }

    public Api getApi() {
        return api;
    }

    public DataBase getDataBase() {
        return dataBase;
    }
}








