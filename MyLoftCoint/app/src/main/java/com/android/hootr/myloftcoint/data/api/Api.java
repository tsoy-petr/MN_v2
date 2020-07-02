package com.android.hootr.myloftcoint.data.api;

import com.android.hootr.myloftcoint.data.api.model.RateResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("/InfoBase9/hs/API_Coins/ticker")
    Observable<RateResponse> ticker(@Query("structure") String structure, @Query("convert") String convert);

}
