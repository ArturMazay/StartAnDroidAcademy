package com.example.startandroidacademy.database

import android.provider.BaseColumns

//это контракт согласно которому будет выстраиваться таблица


object FilmContract {

    const val DATABASE_NAME = "movies.db"

    object Movies {
        const val TABLE_NAME = "movies"            //название таблицы
        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_OVERVIEW = "overview"
        const val COLUMN_NAME_VOTE_AVERAGE = "voteAverage"
        const val COLUMN_NAME_VOTE_COUNT = "voteCount"
        const val COLUMN_NAME_POSTER_URL = "poster"
        const val COLUMN_NAME_BACKDROP_URL = "backdrop"
        const val COLUMN_NAME_GENRES_NAMES = "genres"
        const val COLUMN_NAME_ACTORS = "actors"
    }

    object Actors {
        const val TABLE_NAME = "actors"
        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_IMAGE_URL = "picture"
    }
}