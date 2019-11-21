package com.android.hootr.gactranslator;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    public LiveData<Long> getTime() {
        MyApplication application = MyApplication.getInstance();
        Repository repository = Repository.getInstance(application);
        return repository.getTime();
    }

    public void start() {

        Context context = MyApplication.getInstance();
        Intent intent = new Intent(context, MyIntentService.class);
        context.startService(intent);

    }
}
