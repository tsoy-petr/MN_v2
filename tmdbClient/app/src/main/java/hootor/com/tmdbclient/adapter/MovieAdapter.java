package hootor.com.tmdbclient.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hootor.com.tmdbclient.R;
import hootor.com.tmdbclient.databinding.MovieListItemBinding;
import hootor.com.tmdbclient.model.Movie;
import hootor.com.tmdbclient.view.MovieActivity;

public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MovieViewHolder> {

    private Context context;
//    private ArrayList<Movie> movieArrayList;

    public MovieAdapter(Context context
//            , ArrayList<Movie> movieArrayList
    ) {
        super(Movie.CALLBACK);
        this.context = context;
//        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MovieListItemBinding movieListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.movie_list_item, parent, false);

//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);
        return new MovieViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

//        holder.movieTitle.setText(movieArrayList.get(position).getOriginalTitle());
//        holder.rate.setText(Double.toString(movieArrayList.get(position).getVoteAverage()));

        Movie movie = getItem(position);

//        String imagePath = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();
//        movie.setPosterPath(imagePath);

        holder.movieListItemBinding.setMovie(movie);

    }

//    @Override
//    public int getItemCount() {
//        return movieArrayList.size();
//    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private MovieListItemBinding movieListItemBinding;

//        public TextView movieTitle, rate;
//        public ImageView movieImage;

        public MovieViewHolder(@NonNull MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
//            super(itemView);

            this.movieListItemBinding = movieListItemBinding;

//            movieImage = (ImageView) itemView.findViewById(R.id.ivMovie);
//            rate = (TextView) itemView.findViewById(R.id.tvRating);
//            movieTitle = (TextView) itemView.findViewById(R.id.tvTitle);

            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {

                        Movie selctedMovie = getItem(position);

                        Intent intent = new Intent(context, MovieActivity.class);
                        intent.putExtra("movie", selctedMovie);
                        context.startActivity(intent);


                    }


                }
            });


        }
    }
}
