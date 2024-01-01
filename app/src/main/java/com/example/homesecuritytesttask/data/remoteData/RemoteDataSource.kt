package com.example.homesecuritytesttask.data.remoteData

import com.example.homesecuritytesttask.domain.CamerasResponse
import com.example.homesecuritytesttask.domain.DoorsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class RemoteDataSource(val client: HttpClient) {

    suspend fun getCameras(): CamerasResponse {
        val response: CamerasResponse = client.get {
            url("http://cars.cprogroup.ru/api/rubetek/cameras/")
        }.body()
        return response
    }

    suspend fun getDoors(): DoorsResponse {
        val response: DoorsResponse = client.get {
            url("http://cars.cprogroup.ru/api/rubetek/doors/")
        }.body()
        return response
    }
}