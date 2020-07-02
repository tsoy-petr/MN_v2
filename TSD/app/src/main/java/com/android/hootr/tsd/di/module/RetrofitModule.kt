package com.android.hootr.tsd.di.module

import com.android.hootr.tsd.prefs.PrefsImp
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [PrefsModule::class])
class RetrofitModule() {

    @Singleton
    @Provides
    fun retrofit(
        prefs: PrefsImp,
        gsonConverterFactory: GsonConverterFactory,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
//            .client(okHttpClient)
            .baseUrl(prefs.getURL1CServer())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Singleton
    @Provides
    fun gsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }
}