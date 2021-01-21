package com.example.startandroidacademy.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ActorResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("cast")
    val cast: List<JsonActor>,
)

@Serializable
data class JsonActor(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("profile_path")
    val profilePath: String?
)