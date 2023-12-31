package com.example.homesecuritytesttask.data.repository

import com.example.homesecuritytesttask.data.DataResult
import com.example.homesecuritytesttask.domain.Camera
import com.example.homesecuritytesttask.domain.Door

interface RepositoryRemote {
    suspend fun getCameras() : DataResult<List<Camera>>

    suspend fun getDoors(): DataResult<List<Door>>
}