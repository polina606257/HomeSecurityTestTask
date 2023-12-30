package com.example.homesecuritytesttask.data.remoteData

import com.example.homesecuritytesttask.domain.CamerasResponse
import com.example.homesecuritytesttask.domain.DoorsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RemoteDataSource {
    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }
    }

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