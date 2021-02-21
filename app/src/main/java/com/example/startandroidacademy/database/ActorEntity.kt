package com.example.startandroidacademy.database

import androidx.room.*

@Entity(
    tableName = "actor",
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movie_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["movie_id"])
    ]
)
data class ActorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "movie_id")
    val movieId: Long,
    @ColumnInfo(name = "actor_id")
    val actorId: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "picture_url")
    val picture: String
)
