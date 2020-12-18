package com.example.startandroidacademy.data

import java.io.Serializable


data class Movie(
    val id: Int,
    val title: String?,
    val overview: String?,
    val poster: String?,
    val backdrop: String?,
    val ratings: Float,
    val numberOfRatings: Int,
    val minimumAge: Int,
    val runtime: Int,
    val genres: List<Genre>,
    val actors: List<Actor>
) :Serializable {

    fun getReview(): String {
        return "123 Reviews"
    }

    fun getRuntime(): String {
        return "$runtime min"
    }


    fun getRating(): Float {
        return if (ratings <= 0) 0F else ratings / 2
    }

    fun getTag(): String {
        return genres.joinToString(separator = ", ", transform = { genreItem -> genreItem.name })
    }

    fun getMinimumAge(): Int? {
        return  minimumAge
    }

}