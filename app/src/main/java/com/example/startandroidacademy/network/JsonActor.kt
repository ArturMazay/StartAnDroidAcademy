package com.example.startandroidacademy.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
private class JsonActor(
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val profilePicture: String
)