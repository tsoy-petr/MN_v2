package hootor.com.loftcoint19.data.api;

import hootor.com.loftcoint19.data.api.model.RateResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

//    @GET("cryptocurrency/listings/latest")
//    @Headers("X-CMC_PRO_API_KEY: fcfba74c-7d14-4b00-8009-481952389415")
//    Call<RateResponse> ticker(@Query("start") String start, @Query("limit") String limit, @Query("convert") String convert);

    @GET("tsoy-petr/testCoints/db")
    Observable<RateResponse> ticker();
}
