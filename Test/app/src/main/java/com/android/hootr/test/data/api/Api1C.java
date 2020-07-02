package com.android.hootr.test.data.api;

import com.android.hootr.test.data.api.models.login.LoginResultServer;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api1C {

    @GET("/OrderBase/hs/API_Orders/login")
    Observable<LoginResultServer> login(@Query("codeUser") String structure, @Query("id_gadget") String idGatget);

}
