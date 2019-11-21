package com.android.hootr.gactranslator;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

public class Repository {

    Context appContext;

    private Repository(){ }

    public Repository(Context appContext) {
        this.appContext = appContext;
    }

    private static Repository instance;

    public static synchronized Repository getInstance(Context context) {
        if (instance == null) {
            instance = new Repository(context);
        }
        return instance;
    }

    private MutableLiveData<Long> time = new MutableLiveData<>();

    public LiveData<Long> getTime() {
        return time;
    }

    public void newTime(long l) {
        time.postValue(l);
    }

    private static TranslateResultDb db;

    public static synchronized TranslateResultDb getDb(){
        if (db == null) {
            Context context = MyApplication.getInstance();
            db = Room.databaseBuilder(
                    context,
                    TranslateResultDb.class,
                    "translate.db"
            ).build();
        }

        return db;
    }

    public LiveData<TranslateResalt> translate(String sentence, String lang) {

        Context context = MyApplication.getInstance();
        Intent service = new Intent(context, TranslateService.class);
        service.putExtra(TranslateService.SENTENCE, sentence);
        service.putExtra(TranslateService.LANG, lang);

        context.startService(service);

        return getDb().translateResultDao().translate(sentence, lang);
    }
}
