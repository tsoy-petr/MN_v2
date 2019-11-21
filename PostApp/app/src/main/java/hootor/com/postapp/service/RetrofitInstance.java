package hootor.com.postapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit= null;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static Object BLOK = new Object();

    public static PostAppService getService() {

        synchronized (BLOK) {
            if (retrofit == null) {
                retrofit = new Retrofit
                        .Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }

        return retrofit.create(PostAppService.class);
    }
}
