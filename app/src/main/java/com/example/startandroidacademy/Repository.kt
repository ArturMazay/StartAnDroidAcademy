package com.example.startandroidacademy

import android.content.Context
import com.example.startandroidacademy.data.loadMovies


class Repository( val context: Context) {

  suspend fun  loadMovies() = loadMovies(context)
}