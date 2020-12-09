package com.example.startandroidacademy

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.startandroidacademy.data.Actor

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val actorImageImageView: ImageView = itemView.findViewById(R.id.image_view_actor)
    private val actorNameTextView: TextView = itemView.findViewById(R.id.name_actor)

    fun bind(actor: Actor) {
        actorImageImageView.load(R.drawable.actor2)
        actorNameTextView.text = actor.actorName
    }

}
