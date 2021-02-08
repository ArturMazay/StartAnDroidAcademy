package com.example.startandroidacademy.database

import androidx.room.*

@Dao
interface MovieDao {

    @Transaction
    @Query("SELECT * FROM movie")
   suspend fun getMovieListWithGenre(): List<MovieListWithGenres>

    @Transaction
    @Query("SELECT * FROM movie")
   suspend fun gentMovieListWithActors(): List<MovieListWithActors>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertAll(movieEntity: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(movieEntity:MovieEntity)

    @Update
   suspend fun update(movieEntity:MovieEntity)

    @Delete
   suspend fun deleteAll()


}