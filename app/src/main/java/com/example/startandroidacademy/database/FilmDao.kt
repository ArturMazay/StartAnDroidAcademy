package com.example.startandroidacademy.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// для обращения в базу данных, интерфейс, для круд оперций

@Dao
interface FilmDao {

    @Query("SELECT * FROM movies")
    suspend fun getAll(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)  //разрешаеться конфлик одинаковых данных заменой
    suspend fun insert(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(actor: ActorsEntity)

    @Query("SELECT * FROM movies WHERE _id == :id")   // : передает аргумент из котлин кода в sql
    suspend fun getById(id: Int): MovieEntity

    @Query("SELECT * FROM actors WHERE _id == :id")
    suspend fun getActorById(id: Int): ActorsEntity

    @Query("SELECT * FROM actors WHERE _id == :id")
    suspend fun getActorsByIds(id: List<Int>): List<ActorsEntity>

    @Query("DELETE FROM movies WHERE _id == :id")
    suspend fun deleteById(id: Int)

}
