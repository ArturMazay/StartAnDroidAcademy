package com.example.startandroidacademy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class DetailsMovieFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details_movie, container, false)

        val listActor = mutableListOf<Actor>().apply {
            add(Actor("Robert Downey12", R.drawable.actor4))
            add(Actor("Robert Downey31", R.drawable.actor1))
            add(Actor("Robert Downeye1", R.drawable.actor3))
            add(Actor("Robert Downeye1", R.drawable.actor2))
        }

        val adapterActor = ActorAdapter(listActor = listActor)
        val recyclerView: RecyclerView = view.findViewById(R.id.list_actor)
        recyclerView.adapter = adapterActor

        return view
    }

    companion object {

    }
}