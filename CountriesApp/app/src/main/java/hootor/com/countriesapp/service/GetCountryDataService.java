package hootor.com.countriesapp.service;

import hootor.com.countriesapp.model.Info;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetCountryDataService {

    @GET("country/get/all")
    Call<Info> gerResults();
}
