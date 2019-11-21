package hootor.com.loftcoint19.screens.launch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hootor.com.loftcoint19.App;
import hootor.com.loftcoint19.data.prefs.Prefs;
import hootor.com.loftcoint19.screens.start.StartActivity;
import hootor.com.loftcoint19.screens.welcome.WelcomeActivity;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Prefs prefs = ((App) getApplication()).getPrefs();

        if (prefs.isFirstLaunch()) {
            WelcomeActivity.start(this);
        } else {
            StartActivity.start(this);
        }

    }
}
