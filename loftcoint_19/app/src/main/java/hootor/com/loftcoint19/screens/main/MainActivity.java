package hootor.com.loftcoint19.screens.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hootor.com.loftcoint19.R;
import hootor.com.loftcoint19.screens.main.rate.RateFragment;

public class MainActivity extends AppCompatActivity {

    public static void startInNewTask(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(starter);
    }

    private Unbinder unbinder;

    @BindView(R.id.bottom_navigation)
    public BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener
            = item -> {
                switch (item.getItemId()){
                    case R.id.menu_item_accounts:
                        break;
                    case R.id.menu_item_rate:
                        showRateFragment();
                        break;
                    case R.id.menu_item_converter:
                        break;
                }

                return true;
            };

    private void showRateFragment() {
        RateFragment fragment = new RateFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

//    private showWalletsFragment() {
//        WalletsFragment fragment = new WalletsFragment();
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, fragment);
//        fragmentTransaction.commit();
//    }
//
//    private showConverterFragment() {
//        ConverterFragment fragment = new ConverterFragment();
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, fragment);
//        fragmentTransaction.commit();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);


        navigation.setOnNavigationItemSelectedListener(navigationListener);
        navigation.setOnNavigationItemReselectedListener(menuItem -> { });

        if (savedInstanceState == null) {
            navigation.setSelectedItemId(R.id.menu_item_rate);
        }
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
