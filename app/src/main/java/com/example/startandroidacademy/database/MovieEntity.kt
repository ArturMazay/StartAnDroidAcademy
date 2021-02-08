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

data class MovieListWithGenres(
    @Embedded val movieEntity: MovieEntity,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "genre_id",
        associateBy = Junction(MovieWithGenres::class)
    )
    val genres: List<GenreEntity>
)

data class MovieWithActorsAndGenres(
    @Embedded val movieEntity: MovieEntity,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "actor_id",
        associateBy = Junction(MovieWithActors::class)
    )
    val actors: List<ActorEntity>,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "actor_id",
        associateBy = Junction(MovieWithGenres::class)
    )
    val genres: List<GenreEntity>
)

@Entity(primaryKeys = ["movie_id", "genre_id"])
data class MovieWithGenres(
    val movie_id: Long,
    val genre_id: Long,
)

@Entity(primaryKeys = ["movie_id", "actor_id"])
data class MovieWithActors(
    val movie_id: Long,
    val actor_id: Long,
)
