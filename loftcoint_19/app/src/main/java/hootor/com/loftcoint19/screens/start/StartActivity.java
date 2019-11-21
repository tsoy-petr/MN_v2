package hootor.com.loftcoint19.screens.start;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hootor.com.loftcoint19.App;
import hootor.com.loftcoint19.R;
import hootor.com.loftcoint19.data.api.Api;
import hootor.com.loftcoint19.data.db.DataBase;
import hootor.com.loftcoint19.data.db.model.CoinEntityMapper;
import hootor.com.loftcoint19.data.prefs.Prefs;
import hootor.com.loftcoint19.screens.main.MainActivity;

public class StartActivity extends AppCompatActivity implements StartView {

    private Unbinder unbinder;

//    private StartPresenter presenter;


    @BindView(R.id.start_top_corner)
    ImageView topCorner;

    @BindView(R.id.start_bottom_corner)
    ImageView bottomCorner;

    @BindView(R.id.start_outer_circle)
    ImageView outerCircle;

    private StartPresentr presentr;

    public static void start(Context context) {
        Intent starter = new Intent(context, StartActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        unbinder = ButterKnife.bind(this);

        Api api = ((App) getApplication()).getApi();
        Prefs prefs = ((App) getApplication()).getPrefs();
        DataBase dataBase = ((App) getApplication()).getDataBase();
        CoinEntityMapper mapper = new CoinEntityMapper();

        presentr = new StartPresentrImpl(api, prefs, dataBase, mapper);
        presentr.attachView(this);


        presentr.loadRate();
    }

    private void startAnimations() {

        ObjectAnimator innerAnimator = ObjectAnimator.ofFloat(topCorner, "rotation", 0, 360);
        innerAnimator.setDuration(30000);
        innerAnimator.setRepeatMode(ValueAnimator.RESTART);
        innerAnimator.setRepeatCount(ValueAnimator.INFINITE);
        innerAnimator.setInterpolator(new LinearInterpolator());

        ObjectAnimator outerAnimator = ObjectAnimator.ofFloat(bottomCorner, "rotation", 0, -360);
        outerAnimator.setDuration(55000);
        outerAnimator.setRepeatMode(ValueAnimator.RESTART);
        outerAnimator.setRepeatCount(ValueAnimator.INFINITE);
        outerAnimator.setInterpolator(new LinearInterpolator());

        AnimatorSet set = new AnimatorSet();
        set.play(innerAnimator).with(outerAnimator);
        set.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startAnimations();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        presentr.detachView();
        super.onDestroy();
    }

    @Override
    public void navigatToMainActivity() {
        MainActivity.startInNewTask(this);
    }
}
