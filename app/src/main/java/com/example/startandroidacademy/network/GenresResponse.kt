package com.example.startandroidacademy.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GenresResponse (
    @SerialName("genre_ids")
    val genres: List<JsonGenre>
)

@Serializable
data class JsonGenre (
    val id: Int,
    val name: String
)