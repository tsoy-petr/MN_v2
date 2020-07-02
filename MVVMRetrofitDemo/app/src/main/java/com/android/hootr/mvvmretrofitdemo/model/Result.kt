package com.android.hootr.mvvmretrofitdemo.model

import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.android.hootr.mvvmretrofitdemo.R
import com.bumptech.glide.Glide
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    @SerializedName("popularity")
    @Expose
    @get:Bindable
    val popularity: Double,


    @SerializedName("vote_count")
    @Expose
    @get:Bindable
    val voteCount: Int,

    @SerializedName("video")
    @Expose
    @get:Bindable
    val video: Boolean,

    @SerializedName("poster_path")
    @Expose
    @get:Bindable
    val posterPath: String,

    @SerializedName("id")
    @Expose
    @get:Bindable
    val id: Int,

    @SerializedName("adult")
    @Expose
    @get:Bindable
    val adult: Boolean,

    @SerializedName("backdrop_path")
    @Expose
    @get:Bindable
    val backdropPath: String,

    @SerializedName("original_language")
    @Expose
    @get:Bindable
    val originalLanguage: String,

    @SerializedName("original_title")
    @Expose
    @get:Bindable
    val originalTitle: String,

    @SerializedName("genre_ids")
    @Expose
    @get:Bindable
    val genreIds: List<Int>,

    @SerializedName("title")
    @Expose
    @get:Bindable
    val title: String,

    @SerializedName("vote_average")
    @Expose
    @get:Bindable
    val voteAverage: Double,

    @SerializedName("overview")
    @Expose
    @get:Bindable
    val overview: String,

    @SerializedName("release_date")
    @Expose
    @get:Bindable
    val releaseDate: String

) : Parcelable, BaseObservable() {

    companion object {

        @BindingAdapter("posterPath")
        fun loadImage(imageView: ImageView, imageUrl: String) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .placeholder(R.drawable.progress_circle)
                .into(imageView);
        }

    }

}