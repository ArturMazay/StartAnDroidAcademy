package com.example.startandroidacademy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)


        val listActor = mutableListOf<Actor>().apply {
            add(Actor("Robert Downey12", R.drawable.actor4))
            add(Actor("Robert Downey31", R.drawable.actor1))
            add(Actor("Robert Downeye1", R.drawable.actor3))
            add(Actor("Robert Downeye1", R.drawable.actor2))

        }


        val adapterActor = ActorAdapter(listActor = listActor)
        val recyclerView: RecyclerView = findViewById(R.id.list_actor)

        recyclerView.adapter = adapterActor
    }
}