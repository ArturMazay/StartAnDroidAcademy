package com.example.startandroidacademy.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = FilmContract.Actors.TABLE_NAME,
    indices = [Index(FilmContract.Actors.COLUMN_NAME_ID)]
)
data class ActorsEntity(
    @PrimaryKey()
    @ColumnInfo(name = FilmContract.Actors.COLUMN_NAME_ID)
    val id: Int,
    @ColumnInfo(name = FilmContract.Actors.COLUMN_NAME_NAME)
    val name: String,
    @ColumnInfo(name = FilmContract.Actors.COLUMN_NAME_IMAGE_URL)
    val picture: String
)