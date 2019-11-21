package hootor.com.loftcoint19.screens.welcome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hootor.com.loftcoint19.App;
import hootor.com.loftcoint19.R;
import hootor.com.loftcoint19.data.prefs.Prefs;
import hootor.com.loftcoint19.screens.start.StartActivity;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.pager)
    ViewPager pager;

    @BindView(R.id.start_btn)
    Button startBtn;

    private Unbinder unbinder;

    public static void start(Context context) {
        Intent starter = new Intent(context, WelcomeActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        unbinder = ButterKnife.bind(this);

        Prefs prefs = ((App) getApplication()).getPrefs();

        pager.setAdapter(new WelcomePagerAdapter(getSupportFragmentManager()));

        startBtn.setOnClickListener(v -> {
            prefs.setFirstLaunch(false);
            StartActivity.start(getApplicationContext());
        });

    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
