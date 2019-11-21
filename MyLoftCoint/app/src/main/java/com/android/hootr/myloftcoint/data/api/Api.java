package com.android.hootr.myloftcoint.data.api;

import com.android.hootr.myloftcoint.data.api.model.RateResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("/InfoBase9/hs/API_Coins/ticker")
    Call<RateResponse> ticker(@Query("structure") String structure, @Query("convert") String convert);

}
