package hootor.com.tmdbclient.model;

import android.app.Application;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import hootor.com.tmdbclient.R;
import hootor.com.tmdbclient.service.MovieDataService;
import hootor.com.tmdbclient.service.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> {

    private MovieDataService movieDataService;
    private Application application;

    public MovieDataSource(MovieDataService movieDataService, Application application) {
        this.movieDataService = movieDataService;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Movie> callback) {

        movieDataService = RetrofitInstance.getService();
        Call<MovieDBResponse> call = movieDataService.getPopularMoviesWithPaging(
                application.getApplicationContext().getString(R.string.api_key)
                , 1
        );

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();
                ArrayList<Movie> movies = new ArrayList<>();
                if (movieDBResponse != null
                        && movieDBResponse.getResults() != null) {
                    movies = (ArrayList<Movie>) movieDBResponse.getResults();

                    callback.onResult(movies, null, (long) 2);
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Movie> callback) {

        movieDataService = RetrofitInstance.getService();
        Call<MovieDBResponse> call = movieDataService.getPopularMoviesWithPaging(
                application.getApplicationContext().getString(R.string.api_key)
                , params.key
        );

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();
                ArrayList<Movie> movies = new ArrayList<>();

                if (movieDBResponse != null
                        && movieDBResponse.getResults() != null) {
                    movies = (ArrayList<Movie>) movieDBResponse.getResults();
                    callback.onResult(movies, params.key + 1);
                }

            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
    }
}
