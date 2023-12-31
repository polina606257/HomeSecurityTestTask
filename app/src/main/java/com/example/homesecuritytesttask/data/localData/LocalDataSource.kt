package com.example.homesecuritytesttask.data.localData

import com.example.homesecuritytesttask.domain.Camera
import com.example.homesecuritytesttask.domain.Door
import io.realm.Realm

class LocalDataSource {
    fun addCamera(camera: Camera) {
        Realm
            .getDefaultInstance()
            .executeTransaction { realm ->
                val objectPerson = realm.createObject(Camera::class.java)
                objectPerson.id = camera.id
                objectPerson.name = camera.name
                objectPerson.snapshot = camera.snapshot
                objectPerson.room = camera.room
                objectPerson.favorites = camera.favorites
                objectPerson.rec = camera.rec
            }
    }

    fun getAllCameras(): List<Camera> {
        val data = Realm
            .getDefaultInstance()
            .where(Camera::class.java)
            .findAll()
        return data.map { it }
    }

    fun addDoor(door: Door) {
        Realm
            .getDefaultInstance()
            .executeTransaction { realm ->
                val objectPerson = realm.createObject(Door::class.java)
                objectPerson.id = door.id
                objectPerson.name = door.name
                objectPerson.room = door.room
                objectPerson.favorites = door.favorites
                objectPerson.snapshot = door.snapshot
            }
    }

    fun getAllDoors(): List<Door> {
        val data = Realm
            .getDefaultInstance()
            .where(Door::class.java)
            .findAll()
        return data.map { it }
    }
}