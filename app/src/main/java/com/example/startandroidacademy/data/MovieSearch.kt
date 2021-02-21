package com.example.startandroidacademy.data

import java.io.Serializable

data class MovieSearch(
    val id: Int,
    val title: String?,
    val overview: String?,
    val poster: String?,
    val backdrop: String?,
    val voteAverage: Float, // звезды
    val voteCount: Int, //голоса кол во
    val adult: Int
):Serializable
