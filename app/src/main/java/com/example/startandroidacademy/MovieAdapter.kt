package com.example.startandroidacademy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.startandroidacademy.data.Movie

class MovieAdapter(
    private val onClickListenerToMovieDetails: OnClickListenerToMovieDetails?
) : RecyclerView.Adapter<MovieViewHolder>() {

    var data = mutableListOf<Movie>()
        set(value) {
            field = value
        }

    /*private val listMovie: List<Movie> = mutableListOf()
       *//* set(value) {
            field = value
        }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(data[position])

        holder.itemView.setOnClickListener {
            val movie = data[position]
            onClickListenerToMovieDetails?.onClickOpenDetailsMovieFragment(movie)
        }
    }

    override fun getItemCount(): Int = data.size

    /*fun bindMovies(newMovies: MutableList<Movie>) {
        newMovies.run {
            clear()
            addAll(listMovie)
        }

    }*/
}