package com.example.startandroidacademy

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.startandroidacademy.data.Actor

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val actorImageImageView: ImageView = itemView.findViewById(R.id.actor_poster)
    private val actorNameTextView: TextView = itemView.findViewById(R.id.name_actor)

    fun bind(actor: Actor) {
        Glide.with(itemView.context).load(actor.pictureLink).into(actorImageImageView)
        actorNameTextView.text = actor.name
    }

}
