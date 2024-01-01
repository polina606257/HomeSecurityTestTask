package com.example.homesecuritytesttask.data.localData

import com.example.homesecuritytesttask.domain.Camera
import com.example.homesecuritytesttask.domain.Door
import io.realm.Realm

class LocalDataSource {
    fun addCamera(camera: Camera) {
        Realm
            .getDefaultInstance()
            .executeTransaction { realm ->
                val objectCamera = realm.createObject(Camera::class.java, camera.id)
                objectCamera.name = camera.name
                objectCamera.snapshot = camera.snapshot
                objectCamera.room = camera.room
                objectCamera.favorites = camera.favorites
                objectCamera.rec = camera.rec
            }
    }

    fun getAllCameras(): List<Camera> {
        Realm.getDefaultInstance().use { realm ->
            val data = realm.where(Camera::class.java)
                .findAll()
            return realm.copyFromRealm(data)
        }
    }

    fun updateCamera(camera: Camera) {
        Realm.getDefaultInstance().executeTransaction { realm ->
            val existingCamera = realm.where(Camera::class.java)
                .equalTo("id", camera.id)
                .findFirst()

            existingCamera?.apply {
                name = camera.name
                snapshot = camera.snapshot
                room = camera.room
                favorites = camera.favorites
                rec = camera.rec
            }
        }
    }

    fun addDoor(door: Door) {
        Realm
            .getDefaultInstance()
            .executeTransaction { realm ->
                val objectPerson = realm.createObject(Door::class.java, door.id)
                objectPerson.name = door.name
                objectPerson.room = door.room
                objectPerson.favorites = door.favorites
                objectPerson.snapshot = door.snapshot
            }
    }

    fun getAllDoors(): List<Door> {
        Realm.getDefaultInstance().use { realm ->
            val data = realm.where(Door::class.java)
                .findAll()
            return realm.copyFromRealm(data)
        }
    }

    fun updateDoor(door: Door) {
        Realm.getDefaultInstance().executeTransaction { realm ->
            val existingDoor = realm.where(Door::class.java)
                .equalTo("id", door.id)
                .findFirst()

            existingDoor?.apply {
                name = door.name
                snapshot = door.snapshot
                room = door.room
                favorites = door.favorites
            }
        }
    }
}