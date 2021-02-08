package com.example.startandroidacademy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class,ActorEntity::class,GenreEntity::class], version = 1,  exportSchema = false)
abstract class MovieDataBase():RoomDatabase() {

    abstract val movieDao: MovieDao

    companion object {
        private var INSTANCE: MovieDataBase? = null
        fun getInstance(context: Context): MovieDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDataBase::class.java,
                        "movie"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}