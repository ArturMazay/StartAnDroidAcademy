package com.example.startandroidacademy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView


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

        val listActor = mutableListOf<Actor>().apply {
            add(Actor("Robert Downey12", R.drawable.actor4))
            add(Actor("Robert Downey31", R.drawable.actor1))
            add(Actor("Robert Downeye1", R.drawable.actor3))
            add(Actor("Robert Downeye1", R.drawable.actor2))
        }

        val adapterActor = ActorAdapter(listActor = listActor)
        val recyclerView: RecyclerView? = view?.findViewById(R.id.list_actor)
        recyclerView?.adapter = adapterActor


        val movie = requireArguments().getParcelable<Movie>(MOVIE_KEY)!!  //надо разобраться

    }

        return inflater.inflate(R.layout.fragment_details_movie, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance() = DetailsMovieFragment()

    }
}