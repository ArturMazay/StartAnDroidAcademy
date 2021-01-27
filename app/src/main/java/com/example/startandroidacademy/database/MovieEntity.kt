package com.example.startandroidacademy.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = FilmContract.Movies.TABLE_NAME,
    indices = [Index(FilmContract.Movies.COLUMN_NAME_ID)] //
)
data class MovieEntity(
    @PrimaryKey()
    @ColumnInfo(name = FilmContract.Movies.COLUMN_NAME_ID)
    val id: Int,
    @ColumnInfo(name = FilmContract.Movies.COLUMN_NAME_TITLE)
    val title: String,
    @ColumnInfo(name = FilmContract.Movies.COLUMN_NAME_OVERVIEW)
    val overview: String,
    @ColumnInfo(name = FilmContract.Movies.COLUMN_NAME_VOTE_AVERAGE)
    val voteAverage: Float,
    @ColumnInfo(name = FilmContract.Movies.COLUMN_NAME_VOTE_COUNT)
    val voteCount: Int,
    @ColumnInfo(name = FilmContract.Movies.COLUMN_NAME_POSTER_URL)
    val posterUrl: String,
    @ColumnInfo(name = FilmContract.Movies.COLUMN_NAME_BACKDROP_URL)
    val backdropUrl: String,
    @ColumnInfo(name = FilmContract.Movies.COLUMN_NAME_GENRES_NAMES)
    val genresNames: List<String>,
    @ColumnInfo(name = FilmContract.Movies.COLUMN_NAME_ACTORS)
    var actors: List<String>
)
