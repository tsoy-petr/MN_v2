package com.android.hootr.gactranslator;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Repository repository = Repository.getInstance(this);

        for (int i = 0; i < 5; i++) {

            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {

            }

            repository.newTime(System.currentTimeMillis());
        }
    }
}
