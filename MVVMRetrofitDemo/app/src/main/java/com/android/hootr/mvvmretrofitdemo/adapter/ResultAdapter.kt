package com.android.hootr.mvvmretrofitdemo.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.hootr.mvvmretrofitdemo.R
import com.android.hootr.mvvmretrofitdemo.model.Result
import com.android.hootr.mvvmretrofitdemo.view.MovieDetailsActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.result_list_item.view.*


class ResultAdapter(
    val context: Context,
    val results: List<Result>
) : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.result_list_item, parent, false)

        return ResultViewHolder(view)
    }

    override fun getItemCount(): Int = results.size


    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {

        val res = results.get(position)
        holder.titleTextView.text = res.originalTitle
        holder.popularityTextView.text = res.popularity.toString()

        val imagePath = "https://image.tmdb.org/t/p/w500/${res.posterPath}"
        Glide.with(context)
            .load(imagePath)
            .placeholder(R.drawable.progress_circle)
            .into(holder.movieImageView);
    }


    inner class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleTextView = itemView.titleTextView
        val popularityTextView = itemView.popularityTextView
        val movieImageView = itemView.movieImageView

        init {
            itemView.setOnClickListener(View.OnClickListener {

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val result: Result = results.get(position)
                    val intent = Intent(
                        context,
                        MovieDetailsActivity::class.java
                    )
                    intent.putExtra("movieData", result)
                    context.startActivity(intent)
                }
            })
        }
    }
}