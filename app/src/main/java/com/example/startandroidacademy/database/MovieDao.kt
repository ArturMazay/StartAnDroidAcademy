package com.example.startandroidacademy.database

import androidx.room.*

@Dao
interface MovieDao {

    @Transaction
    @Query("SELECT * FROM movie WHERE movie_id=:movieId")
    suspend fun getMovieWithGenresAndActors(movieId: Long): List<MovieWithActorsAndGenres>

    @Transaction
    @Query("SELECT * FROM movie WHERE movie_id=:movieId")
    suspend fun getMoviesWithGenres(movieId: Long): List<MovieWithGenres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<GenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(actors: List<ActorEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovie(movieEntity: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: MovieEntity)

    @Update
    suspend fun update(movieEntity: MovieEntity)

    @Query("DELETE FROM movie")
    fun deleteAll()

}