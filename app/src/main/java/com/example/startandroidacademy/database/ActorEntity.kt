package com.example.startandroidacademy.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "actor"
)
data class ActorEntity(
    @PrimaryKey
    @ColumnInfo(name = "actor_id")
    val actor_id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "picture_actor")
    val picture: String,
    @ColumnInfo(name = "movie_id")
    val movie_id: Int
)
