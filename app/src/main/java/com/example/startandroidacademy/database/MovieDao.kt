package com.example.startandroidacademy.database

import androidx.room.*

@Dao
interface MovieDao {

    @Query(SELECT * FROM movie WHERE movie_id = :movie_id)
    fun getMovieId():List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movieEntity: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieEntity:MovieEntity)

    @Update
    fun update(movieEntity:MovieEntity)

    @Delete
    fun deleteAll()


}