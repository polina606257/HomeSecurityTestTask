package com.example.homesecuritytesttask.domain

import kotlinx.serialization.Serializable

@Serializable
data class CamerasResponse (
    val success: Boolean,
    val data: CamerasData
)

@Serializable
data class CamerasData(
    val room: List<String>,
    val cameras: List<Camera>
)

@Serializable
data class Camera (
    val name: String,
    val snapshot: String,
    val room: String? = null,
    val id: Long,
    val favorites: Boolean,
    val rec: Boolean
)
