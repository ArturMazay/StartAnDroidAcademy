package com.example.startandroidacademy

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide

class MovieViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

    private val titleImageMovie: ImageView = itemView.findViewById(R.id.image_view_list_movie)
    private val movieName: TextView = itemView.findViewById(R.id.tv_movie_list)

    fun bindMovie(movie: Movie) {
        titleImageMovie.setImageResource(R.drawable.moviepng)
        movieName.text = movie.titleMovie
    }

}
