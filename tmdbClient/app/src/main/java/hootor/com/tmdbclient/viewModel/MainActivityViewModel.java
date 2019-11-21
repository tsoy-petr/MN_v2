package hootor.com.tmdbclient.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import hootor.com.tmdbclient.model.Movie;
import hootor.com.tmdbclient.model.MovieDataSource;
import hootor.com.tmdbclient.model.MovieDataSourceFactory;
import hootor.com.tmdbclient.model.MovieRepository;
import hootor.com.tmdbclient.service.MovieDataService;
import hootor.com.tmdbclient.service.RetrofitInstance;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    LiveData<MovieDataSource> movieDataSourceLiveData;

    private Executor executor;

    private LiveData<PagedList<Movie>> moviePagedList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);

        MovieDataService movieDataService = RetrofitInstance.getService();
        MovieDataSourceFactory factory = new MovieDataSourceFactory(movieDataService, application);

        movieDataSourceLiveData = factory.getMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(5);

        moviePagedList = (new LivePagedListBuilder<Long, Movie>(factory, config))
                .setFetchExecutor(executor)
                .build();

    }

    public LiveData<PagedList<Movie>> getMoviePagedList() {
        return moviePagedList;
    }

    public LiveData<List<Movie>> getAllMovies() {
        return movieRepository.getMutableLiveData();
    }
}
