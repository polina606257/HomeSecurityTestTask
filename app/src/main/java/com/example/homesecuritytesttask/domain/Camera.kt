package com.example.homesecuritytesttask.domain

import io.realm.RealmObject
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
open class Camera : RealmObject()  {
    var id: Long = 0
    var name: String = ""
    var snapshot: String = ""
    var room: String? = null
    var favorites: Boolean = false
    var rec: Boolean  = false
}