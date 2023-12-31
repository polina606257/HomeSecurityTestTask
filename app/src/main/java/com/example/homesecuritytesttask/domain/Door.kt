package com.example.homesecuritytesttask.domain

import io.realm.RealmObject
import kotlinx.serialization.Serializable

@Serializable
data class DoorsResponse (
    val success: Boolean,
    val data: List<Door>
)

@Serializable
open class Door : RealmObject() {
    var id: Long = 0
    var name: String = ""
    var room: String? = null
    var favorites: Boolean = false
    var snapshot: String? = null
}