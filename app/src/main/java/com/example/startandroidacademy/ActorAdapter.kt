package com.example.startandroidacademy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.startandroidacademy.data.Actor
import com.example.startandroidacademy.data.Movie

class ActorAdapter ():RecyclerView.Adapter<ActorViewHolder> (){

    private var listActor: List<Actor> = listOf()

    fun bindActor(newActor: List<Actor>){
        listActor=newActor
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder =
         ActorViewHolder(itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.actor_item, parent, false))


    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(listActor[position])
    }

    override fun getItemCount(): Int= listActor.size

}