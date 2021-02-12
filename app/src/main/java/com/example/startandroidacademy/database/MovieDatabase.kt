package com.example.startandroidacademy.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        MovieEntity::class,
        GenreEntity::class,
        ActorEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao

    companion object {
        fun create(applicationContext: Application): MovieDatabase = Room.databaseBuilder(
            applicationContext,
            MovieDatabase::class.java,
            "movies"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
