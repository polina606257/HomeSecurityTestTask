package com.example.homesecuritytesttask.controller

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homesecuritytesttask.data.DataResult
import com.example.homesecuritytesttask.data.repository.RepositoryLocal
import com.example.homesecuritytesttask.data.repository.RepositoryLocalImpl
import com.example.homesecuritytesttask.data.repository.RepositoryRemote
import com.example.homesecuritytesttask.data.repository.RepositoryRemoteImpl
import com.example.homesecuritytesttask.domain.Camera
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CameraScreenViewModel : ViewModel() {
    private val repositoryRemote: RepositoryRemote = RepositoryRemoteImpl()
    private val repositoryLocal: RepositoryLocal = RepositoryLocalImpl()
    private val _cameras: MutableLiveData<List<Camera>> = MutableLiveData()
    val cameras: LiveData<List<Camera>> = _cameras
    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    init {
        viewModelScope.launch {
            val cameras = getCamerasFromDatabase()
            if (cameras?.isEmpty() == true) {
                getCamerasFromServer()
            }
        }
    }

    private fun getCamerasFromServer() {
        viewModelScope.launch {
            when (val cameras = repositoryRemote.getCameras()) {
                is DataResult.Success -> {
                    _cameras.value = cameras.response
                    for (camera in cameras.response) {
                        repositoryLocal.addCamara(camera)
                    }
                }

                is DataResult.Error -> Log.d("TAG", "Can't find any cameras")
            }
            _isRefreshing.value = false
        }
    }

    private suspend fun getCamerasFromDatabase(): List<Camera>? {
        val result = viewModelScope.async {
            when (val cameras = repositoryLocal.getAllCameras()) {
                is DataResult.Success -> {
                    _cameras.postValue(cameras.response)
                    cameras.response
                }
                is DataResult.Error -> {
                    Log.d("TAG", "Can't find any cameras")
                    null
                }
            }
        }
        return result.await()
    }

    fun refresh() {
        _isRefreshing.value = true
        getCamerasFromServer()
    }

    fun updateCamera(camera: Camera) {
        viewModelScope.launch {
            async { repositoryLocal.updateCamera(camera) }.await()
            getCamerasFromDatabase()
        }
    }
}