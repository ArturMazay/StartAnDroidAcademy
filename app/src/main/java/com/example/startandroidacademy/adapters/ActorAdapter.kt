package com.example.startandroidacademy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.startandroidacademy.R
import com.example.startandroidacademy.data.Actor
import com.example.startandroidacademy.utils.DiffCallback
import com.example.startandroidacademy.utils.DiffCallbackActor

class ActorAdapter() : ListAdapter <Actor, ActorViewHolder>(DiffCallbackActor()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder =
        ActorViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.actor_item, parent, false)
        )

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = getItem(position)
        holder.bind(actor)
    }
}