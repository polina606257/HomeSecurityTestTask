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
import com.example.homesecuritytesttask.domain.Door
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DoorScreenViewModel : ViewModel() {
    private val repositoryRemote: RepositoryRemote = RepositoryRemoteImpl()
    private val repositoryLocal: RepositoryLocal = RepositoryLocalImpl()
    private val _doors: MutableLiveData<List<Door>> = MutableLiveData()
    val doors: LiveData<List<Door>> = _doors
    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing


    init {
        viewModelScope.launch {
            val doors = getDoorsFromDatabase()
            if (doors?.isEmpty() == true) {
                getDoorsFromServer()
            }
        }
    }

    private fun getDoorsFromServer() {
        viewModelScope.launch {
            when (val doors = repositoryRemote.getDoors()) {
                is DataResult.Success -> {
                    _doors.value = doors.response
                    for (door in doors.response) {
                        repositoryLocal.addDoor(door)
                    }
                }

                is DataResult.Error -> Log.d("TAG", "Can't find any doors")
            }
            _isRefreshing.value = false
        }
    }

    private suspend fun getDoorsFromDatabase(): List<Door>? {
        val result = viewModelScope.async {
            when (val doors = repositoryLocal.getAllDoors()) {
                is DataResult.Success -> {
                    _doors.postValue(doors.response)
                    doors.response
                }

                is DataResult.Error -> {
                    Log.d("TAG", "Can't find any doors")
                    null
                }
            }
        }
        return result.await()
    }

    fun refresh() {
        _isRefreshing.value = true
        getDoorsFromServer()
    }

    fun updateDoor(door: Door) {
        viewModelScope.launch {
            async { repositoryLocal.updateDoor(door) }.await()
            getDoorsFromDatabase()
        }
    }
}