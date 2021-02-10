package com.example.startandroidacademy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.startandroidacademy.OnClickListenerToMovieDetails
import com.example.startandroidacademy.R
import com.example.startandroidacademy.data.Movie
import com.example.startandroidacademy.utils.DiffCallback

class MovieAdapter(
    private val onClickListenerToMovieDetails: OnClickListenerToMovieDetails?
) : ListAdapter<Movie, MovieViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bindMovie(movie)
        holder.itemView.setOnClickListener {
            onClickListenerToMovieDetails?.onClickOpenDetailsMovieFragment(movie)
        }
    }
}