package com.example.homesecuritytesttask.controller

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homesecuritytesttask.data.DataResult
import com.example.homesecuritytesttask.data.repository.Repository
import com.example.homesecuritytesttask.data.repository.RepositoryImpl
import com.example.homesecuritytesttask.domain.Camera
import kotlinx.coroutines.launch

class CameraScreenViewModel : ViewModel() {
    private val repository: Repository = RepositoryImpl()
    private val _cameras: MutableLiveData<List<Camera>> = MutableLiveData()
    val cameras: LiveData<List<Camera>> = _cameras

    init {
        getCameras()
    }

    private fun getCameras() {
        viewModelScope.launch {
            when(val cameras = repository.getCameras()) {
                is DataResult.Success -> _cameras.value = cameras.response
                is DataResult.Error -> Log.d("TAG", "Can't find any cameras")
            }
        }
    }
}