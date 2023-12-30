package com.example.homesecuritytesttask.data.repository

import com.example.homesecuritytesttask.data.DataResult
import com.example.homesecuritytesttask.data.remoteData.RemoteDataSource
import com.example.homesecuritytesttask.domain.Camera
import com.example.homesecuritytesttask.domain.Door

class RepositoryImpl : Repository {
    private val remoteDataSource: RemoteDataSource = RemoteDataSource()
    override suspend fun getCameras(): DataResult<List<Camera>> {
        return try {
            val response = remoteDataSource.getCameras().data.cameras
            DataResult.Success(response)
        } catch (e: Exception) {
            DataResult.Error(e.message)
        }
    }

    override suspend fun getDoors(): DataResult<List<Door>> {
        return try {
            val response = remoteDataSource.getDoors().data
            DataResult.Success(response)
        } catch (e: Exception) {
            DataResult.Error(e.message)
        }
    }
}