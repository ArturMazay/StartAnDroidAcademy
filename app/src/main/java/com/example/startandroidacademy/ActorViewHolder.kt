package com.example.startandroidacademy

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val actorImageImageView: ImageView = itemView.findViewById(R.id.image_view_actor)
    private val actorNameTextView: TextView = itemView.findViewById(R.id.name_actor)

    fun bind(actor: Actor) {
        //imageView.load(File("/path/to/image.jpg"))
        actorImageImageView.load(R.drawable.actor2)  //надо чтоб картинку из Actor устанавливал а не с дров
        actorNameTextView.text = actor.actorName
    }

}
