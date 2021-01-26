package com.example.startandroidacademy.data

import java.io.Serializable

data class Movie(
    val id: Int,
    val title: String?,
    val overview: String?,
    val poster: String?,
    val backdrop: String?,
    val voteAverage: Float, // звезды
    val voteCount: Int, //голоса кол во
    val adult: Int,
    val genres: List<Genre>
) : Serializable
