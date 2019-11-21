package hootor.com.tmdbclient.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import hootor.com.tmdbclient.R;
import hootor.com.tmdbclient.adapter.MovieAdapter;
import hootor.com.tmdbclient.databinding.ActivityMainBinding;
import hootor.com.tmdbclient.model.Movie;
import hootor.com.tmdbclient.viewModel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private PagedList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("TMDB Popular movies today");

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);


        getPopularMovies();

//        swipeRefreshLayout = findViewById(R.id.swipe_layout);
        swipeRefreshLayout = activityMainBinding.swipeLayout;

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });
    }

    private void getPopularMovies() {

//        mainActivityViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
//            @Override
//            public void onChanged(@Nullable List<Movie> moviesFromLiveData) {
//                movies = (ArrayList<Movie>) moviesFromLiveData;
//                showOnRecyclerView();
//            }
//        });

        mainActivityViewModel.getMoviePagedList().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(@Nullable PagedList<Movie> moviesFromLiveData) {
                movies = moviesFromLiveData;
                showOnRecyclerView();
            }
        });
    }

    private void showOnRecyclerView() {

//        recyclerView = findViewById(R.id.rvMovies);
        recyclerView = activityMainBinding.rvMovies;

        movieAdapter = new MovieAdapter(this);
        movieAdapter.submitList(movies);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

    }
}
