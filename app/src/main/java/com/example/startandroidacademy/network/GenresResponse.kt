package com.example.startandroidacademy.network

import kotlinx.serialization.Serializable


@Serializable
data class GenresResponse (
    val genres: List<JsonGenre>
)

@Serializable
data class JsonGenre (
    val id: Int,
    val name: String
)