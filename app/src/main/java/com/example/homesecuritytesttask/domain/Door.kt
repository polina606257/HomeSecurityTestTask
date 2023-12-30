package com.example.homesecuritytesttask.domain

import kotlinx.serialization.Serializable

@Serializable
data class DoorsResponse (
    val success: Boolean,
    val data: List<Door>
)

@Serializable
data class Door (
    val name: String,
    val room: String? = null,
    val id: Long,
    val favorites: Boolean,
    val snapshot: String? = null
)