package com.example.startandroidacademy.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class SearchMovieresponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val movies: List<JsonMovieSearch>
)
@Serializable
data class JsonMovieSearch(
    val id: Int,
    val title: String?,
    val overview: String?,
    val poster: String?,
    val backdrop: String?,
    val voteAverage: Float, // звезды
    val voteCount: Int, //голоса кол во
    val adult: Int
)
