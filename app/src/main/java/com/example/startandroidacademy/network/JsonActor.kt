package com.example.startandroidacademy.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JsonActor(
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val profilePicture: String
)