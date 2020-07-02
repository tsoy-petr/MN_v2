package com.android.hootr.test.data.api;

import com.android.hootr.test.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api1CInitializer {

    public Api1C init(String baseUrl) {
        Gson gson = createGson();
        OkHttpClient client = createClient();
        Retrofit retrofit = createRetrofit(gson, client, baseUrl);
        return createApi(retrofit);
    }

    private Gson createGson() {
        return new GsonBuilder()
                .create();
    }

    private OkHttpClient createClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BASIC : HttpLoggingInterceptor.Level.NONE);


        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    private Retrofit createRetrofit(Gson gson, OkHttpClient client, String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private Api1C createApi(Retrofit retrofit) {
        return retrofit.create(Api1C.class);
    }

}
