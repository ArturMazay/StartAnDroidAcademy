package com.example.startandroidacademy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.startandroidacademy.data.Actor
import com.example.startandroidacademy.data.Movie

class DetailsMovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewBack: Button = view.findViewById(R.id.button_back)
        viewBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val tvName: TextView = view.findViewById(R.id.tv_name)
        val ivBackground: ImageView = view.findViewById(R.id.iv_background)
        val tag: TextView = view.findViewById(R.id.tv_tag)
        val overview: TextView = view.findViewById(R.id.tv_storyline)

        val movie = requireArguments().getSerializable(MOVIE_KEY) as Movie

        val listActor: List<Actor> = movie.actors
        val adapterActor = ActorAdapter(listActor)
        val recyclerView: RecyclerView? = view.findViewById(R.id.list_actor)
        recyclerView?.adapter = adapterActor
        movie.run {
            Glide.with(view.context).load(movie.poster).into(ivBackground)
            tvName.text = movie.title
            tag.text = movie.getTag()
            overview.text=movie.overview

        }
    }

    companion object {
        const val MOVIE_KEY = "keymovie"

        @JvmStatic
        fun newInstance(movie: Movie) = DetailsMovieFragment().apply {
            arguments = Bundle().apply {
                putSerializable(MOVIE_KEY, movie)
            }
        }
    }
}