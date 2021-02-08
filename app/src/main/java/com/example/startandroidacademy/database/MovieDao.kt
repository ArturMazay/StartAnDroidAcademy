package com.example.startandroidacademy.database

import androidx.room.*

@Dao
interface MovieDao {

    @Transaction
    @Query("SELECT * FROM movie")
    suspend fun getMovieWithGenre(): List<MovieListWithGenres>

    @Transaction
    @Query("SELECT * FROM movie")
    suspend fun gentMovieWithActors(): List<MovieListWithActors>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<GenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(actors: List<ActorEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieEntity: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: MovieEntity)

    @Update
    suspend fun update(movieEntity: MovieEntity)

    @Delete
    suspend fun deleteAll()


}