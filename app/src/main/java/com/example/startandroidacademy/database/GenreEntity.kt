package com.example.startandroidacademy.database

import androidx.room.*

@Entity(tableName = "genre")
class GenreEntity(
    @PrimaryKey
    @ColumnInfo(name = "genre_id")
    val genre_id: Long,
    @ColumnInfo(name = "movie_id")
    val movie_id: Long,
    @ColumnInfo(name = "name_genres")
    val name_genres: String
) {
}