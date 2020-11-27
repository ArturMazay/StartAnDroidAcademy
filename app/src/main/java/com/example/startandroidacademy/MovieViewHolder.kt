package com.example.startandroidacademy

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class MovieViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

    private val titleMovie: ImageView = itemView.findViewById(R.id.image_view_list_movie)
    private val movieName: TextView = itemView.findViewById(R.id.name)

    fun bindMovie(movie: Movie) {
        titleMovie.setImageResource(R.drawable.actor1)  //Вот
        movieName.text = movie.titleMovie
    }

}
