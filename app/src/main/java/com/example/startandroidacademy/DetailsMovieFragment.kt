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


        val adapterActor = ActorAdapter()
        val recyclerView: RecyclerView? = view.findViewById(R.id.list_actor)
        recyclerView?.adapter = adapterActor

        val tvName:TextView = view.findViewById(R.id.tv_name)
        val ivBackgraund:ImageView = view.findViewById(R.id.iv_background)
        val movie = requireArguments().getParcelable<Movie>(MOVIE_KEY)!!

         movie.run {
              Glide.with(view.context).load(movie.poster).into(ivBackgraund)
             tvName.text = title
         }//надо разобраться

    }

    companion object {
        const val MOVIE_KEY = "keymovie"

        @JvmStatic
        fun newInstance(movie: Movie) = DetailsMovieFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MOVIE_KEY, movie)
            }
        }
    }
}