package com.example.startandroidacademy

import android.content.Context
import com.example.startandroidacademy.data.loadMovies


class Repository(private val context: Context) {

  suspend fun  loadMovies() = loadMovies(context) //у миши стащил , пробовал просто в переменную положить не вышло,
                                                 // пока не пометил


}