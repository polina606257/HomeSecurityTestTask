package com.example.homesecuritytesttask.data.repository

import com.example.homesecuritytesttask.data.DataResult
import com.example.homesecuritytesttask.domain.Camera
import com.example.homesecuritytesttask.domain.Door

interface RepositoryLocal {
    suspend fun addCamara(camera: Camera)
    suspend fun getAllCameras() : DataResult<List<Camera>>
    suspend fun addDoor(door: Door)
    suspend fun getAllDoors() : DataResult<List<Door>>
    suspend fun updateCamera(camera: Camera)
    suspend fun updateDoor(door: Door)
}