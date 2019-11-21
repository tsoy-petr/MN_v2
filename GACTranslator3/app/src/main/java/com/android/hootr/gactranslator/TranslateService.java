package com.android.hootr.gactranslator;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TranslateService extends IntentService {

    public static final String LANG = "PARAMETR_LANG";
    public static final String SENTENCE = "PARAMETR_SENTENCE";

    public static final String TRANSLATE_API_KEY = "trnsl.1.1.20170331T163527Z.6d3042c2a9aad0e7.2ce36155bc4a0c5b432344b88b634c25287243c2";
    public static final String TRANSLATE_ENDPOINT = "https://translate.yandex.net";

    private static YandexTranslateService translate =
            new Retrofit.Builder()
                    .baseUrl(TRANSLATE_ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(YandexTranslateService.class);


    public TranslateService() {
        super("TranslateService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        TranslateResalt translateResalt = new TranslateResalt();
        TranslateResultDao dao = Repository.getInstance(this).getDb().translateResultDao();

        String sentence = intent.getStringExtra(SENTENCE);
        String lang = intent.getStringExtra(LANG);

        if (!TextUtils.isEmpty(sentence) && !TextUtils.isEmpty(lang)) {

            Call<Translate> call = translate.translate(TRANSLATE_API_KEY, lang, sentence);

            try {
                Response<Translate> response = call.execute();
                Translate body = response.body();
                String res = body.getText().get(0);
                translateResalt.setResult(res);
            } catch (Exception e) {
                translateResalt.setException(e.getMessage());
                translateResalt.setStatus("ERROR");


            }

            translateResalt.setLang(lang);
            translateResalt.setSentence(sentence);

            dao.deleteAll();
            dao.insertAll(translateResalt);

        }


    }
}
