package com.example.homesecuritytesttask.data.repository

import android.util.Log
import com.example.homesecuritytesttask.data.DataResult
import com.example.homesecuritytesttask.data.localData.LocalDataSource
import com.example.homesecuritytesttask.domain.Camera
import com.example.homesecuritytesttask.domain.Door

class RepositoryLocalImpl(private val localDataSource: LocalDataSource = LocalDataSource()) : RepositoryLocal {
    override suspend fun addCamara(camera: Camera) {
        try {
            localDataSource.addCamera(camera)
        } catch (e: Exception) {
            Log.d("TAG", "Couldn't add camera")
        }
    }

    override suspend fun getAllCameras(): DataResult<List<Camera>> {
        return try {
            val cameras = localDataSource.getAllCameras()
            DataResult.Success(cameras)
        } catch (e: Exception) {
            DataResult.Error(e.message)
        }
    }

    override suspend fun addDoor(door: Door) {
        try {
            localDataSource.addDoor(door)
        } catch (e: Exception) {
            Log.d("TAG", "Couldn't add door")
        }
    }

    override suspend fun getAllDoors(): DataResult<List<Door>> {
        return try {
            val doors = localDataSource.getAllDoors()
            DataResult.Success(doors)
        } catch (e: Exception) {
            DataResult.Error(e.message)
        }
    }

    override suspend fun updateCamera(camera: Camera) {
        try {
            localDataSource.updateCamera(camera)
        } catch (e: Exception) {
            Log.d("TAG", "Couldn't update camera")
        }
    }

    override suspend fun updateDoor(door: Door) {
        try {
            localDataSource.updateDoor(door)
        } catch (e: Exception) {
            Log.d("TAG", "Couldn't update door")
        }
    }
}