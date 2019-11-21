package hootor.com.workmanagetdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final OneTimeWorkRequest oneTimeWorkRequest =
                new OneTimeWorkRequest.Builder(DemoWorker.class).build();


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkManager.getInstance().enqueue(oneTimeWorkRequest);
            }
        });
    }
}
