package com.example.startandroidacademy.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GenresResponse (
    val genres: List<JsonGenre>
)

@Serializable
data class JsonGenre (
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)