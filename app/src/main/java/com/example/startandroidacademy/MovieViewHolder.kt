package com.example.startandroidacademy

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.startandroidacademy.data.Movie

class MovieViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

    private val poster: ImageView = itemView.findViewById(R.id.image_view_list_movie)
    private val tvName: TextView = itemView.findViewById(R.id.tv_movie_name)
    private val tvAge: TextView = itemView.findViewById(R.id.age_text)
    private val tvTime: TextView = itemView.findViewById(R.id.time_text)
    private val reviews:TextView = itemView.findViewById(R.id.reviews)
    private val tag:TextView = itemView.findViewById(R.id.tag_movie_list)
    private val ratingBar:RatingBar=itemView.findViewById(R.id.rating)

    fun bindMovie(movie: Movie) {
        Glide.with(itemView.context).load(movie.poster).into(poster)
        tvName.text = movie.title
        ratingBar.rating = movie.getRating()
        tvAge.text = movie.getMinimumAge().toString()+ "+"//
        tvTime.text = movie.getRuntime()
        reviews.text = movie.getReview()
        tag.text = movie.getTag()
    }

}
