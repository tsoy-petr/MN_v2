package hootor.com.tmdbclient.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import hootor.com.tmdbclient.R;
import hootor.com.tmdbclient.databinding.ActivityMovieBinding;
import hootor.com.tmdbclient.model.Movie;

public class MovieActivity extends AppCompatActivity {

    private Movie movie;
//    private ImageView movieImage;
//    private String image;
//
//    private TextView movieTitle, movieSynopis, movieRating, movieReleaseDate;

    private ActivityMovieBinding activityMovieBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);

//        movieImage = findViewById(R.id.ivMovieLarge);
//        movieTitle = findViewById(R.id.tvMovieTitle);
//        movieSynopis = findViewById(R.id.tvPlotsynopsis);
//        movieRating = findViewById(R.id.tvMovieRating);
//        movieReleaseDate = findViewById(R.id.tvReleaseDate);

        Intent intent = getIntent();

        if (intent.hasExtra("movie")) {
            movie = intent.getParcelableExtra("movie");

            activityMovieBinding.setMovie(movie);

//            image = movie.getPosterPath();
//
//            String path = "https://image.tmdb.org/t/p/w500" + image;
//
//            Glide.with(this)
//                    .load(path)
//                    .placeholder(R.drawable.loading)
//                    .into(movieImage);

            getSupportActionBar().setTitle(movie.getTitle());

//            movieTitle.setText(movie.getTitle());
//            movieSynopis.setText(movie.getOverview());
//            movieRating.setText(Double.toString(movie.getVoteAverage()));
//            movieReleaseDate.setText(movie.getReleaseDate());
        }
    }

}
