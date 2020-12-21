package com.example.startandroidacademy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.startandroidacademy.data.Movie

class MovieAdapter(
    private val onClickListenerToMovieDetails: OnClickListenerToMovieDetails?
) : RecyclerView.Adapter<MovieViewHolder>() {

    private var listMovie: List<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(listMovie[position])

        holder.itemView.setOnClickListener {
            val movie = listMovie[position]
            onClickListenerToMovieDetails?.onClickOpenDetailsMovieFragment(movie)
        }
    }

    override fun getItemCount(): Int = listMovie.size

    fun bindMovies(newMovies: MutableList<Movie>) {
        newMovies.run {
            clear()
            addAll(listMovie)
        }

    }
}