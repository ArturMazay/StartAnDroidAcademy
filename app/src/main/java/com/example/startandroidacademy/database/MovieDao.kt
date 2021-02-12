package com.example.startandroidacademy.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

// для обращения в базу данных, интерфейс, для круд оперций

@Dao
interface MovieDao {
    @Insert
    fun insertMovies(movies: List<MovieEntity>)

    @Insert
    fun insertGenres(genres: List<GenreEntity>)

    @Insert
    fun insertActors(actors: List<ActorEntity>)

    @Query("DELETE FROM actor WHERE movie_id = :movieId")
    fun deleteActorsByMovie(movieId: Long)

    @Query("DELETE FROM movie")
    fun deleteAll()

    @Transaction
    @Query("SELECT * FROM movie")
    fun getMoviesWithGenres(): MovieWithGenres

    @Transaction
    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun getMovieWithGenresAndActors(movieId: Long): MovieWithGenresAndActors
}