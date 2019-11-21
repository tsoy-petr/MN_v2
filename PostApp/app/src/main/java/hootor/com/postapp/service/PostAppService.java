package hootor.com.postapp.service;

import hootor.com.postapp.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostAppService {

    @POST("posts")
    Call<User> getResult(@Body User user);
}
