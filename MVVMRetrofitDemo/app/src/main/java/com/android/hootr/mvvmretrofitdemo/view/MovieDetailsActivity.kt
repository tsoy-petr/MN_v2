package com.android.hootr.mvvmretrofitdemo.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.hootr.mvvmretrofitdemo.R
import com.android.hootr.mvvmretrofitdemo.model.Result
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_details.*



class MovieDetailsActivity : AppCompatActivity() {

    private var result: Result? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        if(intent != null && intent.hasExtra("movieData")) {
            result = intent.getParcelableExtra("movieData")

            result?.let {
                Toast.makeText(this, it.originalTitle,
                    Toast.LENGTH_LONG).show()
            }

            val posterPath = result?.posterPath
            val imagePath = "https://image.tmdb.org/t/p/w500/$posterPath"

            Glide.with(this)
                .load(imagePath)
                .placeholder(R.drawable.progress_circle)
                .into(imageView)

            titleTextView.text = result?.originalTitle
            voteCountTextView.text = result?.voteCount.toString()
            overviewTextView.text = result?.overview

        }

    }
}
