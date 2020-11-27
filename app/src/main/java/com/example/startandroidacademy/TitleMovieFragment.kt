package com.example.startandroidacademy

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView


class TitleMovieFragment : Fragment() {

    private var onClickListenerToMovieDetails: OnClickListenerToMovieDetails? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_title_movie, container, false)
        /* val button:Button=view.findViewById(R.id.button_open)
         button.setOnClickListener {
             onClickListenerToMovieDetails?.onClickOpenDetailsMovieFragment()
             //Log.e("XXX",("WORK GOOD").toString())
         }*/

        return view
    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

         val listMovie = mutableListOf<Movie>().apply {
             add(Movie("Robert Downey12", R.drawable.moviepng))
             Log.e("XXX","WORK GOOD")
         }

         val adapterMovie = MovieAdapter(listMovie=listMovie)

         val rv: RecyclerView? = view.findViewById(R.id.rv_movie)
         Log.e("XXX",(rv==null).toString())
         rv?.adapter = adapterMovie



     }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onClickListenerToMovieDetails = context as? OnClickListenerToMovieDetails
        // Log.e("XXX",(onClickListenerToMovieDetails==null).toString())
    }

    override fun onDetach() {
        super.onDetach()
        onClickListenerToMovieDetails = null
        //Log.e("XXX",(onClickListenerToMovieDetails==null).toString())
    }

    companion object {

    }
}