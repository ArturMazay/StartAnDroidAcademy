package com.example.startandroidacademy.database

import androidx.room.*

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movie_id: Long,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "overview")
    val overview: String?,
    @ColumnInfo(name = "poster")
    val poster: String?,
    @ColumnInfo(name = "backdrop")
    val backdrop: String?,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Float,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int,
    @ColumnInfo(name = "adult")
    val adult: Int
)

@Entity(primaryKeys = ["movie_id","genre_id"])
data class MovieWithGenres(
    val movie_id: Long,
    val genre_id: Long,
)

@Entity(primaryKeys = ["movie_id","actor_id"])
data class MovieWithActors(
    val movie_id: Long,
    val actor_id: Long,
)
