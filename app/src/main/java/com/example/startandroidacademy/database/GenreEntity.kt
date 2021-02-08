package com.example.startandroidacademy.database

import androidx.room.*

@Entity(
    tableName = "genre",
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = ["genre_id"],
        childColumns = ["movie_id"],
        onDelete = ForeignKey.CASCADE
    )], indices = [Index(value = ["movie_id"])]
)
class GenreEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "genre_id")
    val genre_id: Long,
    @ColumnInfo(name = "movie_id")
    val movie_id: Long,
    @ColumnInfo(name = "name_genres")
    val name_genres: String
) {
}