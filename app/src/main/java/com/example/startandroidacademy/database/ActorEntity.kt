package com.example.startandroidacademy.database

import androidx.room.*

@Entity(
    tableName = "actor",
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = ["actor_id"],
        childColumns = ["movie_id"],
        onDelete = ForeignKey.CASCADE
    )], indices = [Index(value = ["movie_id"])]
)
data class ActorEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "actor_id")
    val actor_id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "picture_actor")
    val picture: String,
    @ColumnInfo(name = "movie_id")
    val movie_id: Int
)
