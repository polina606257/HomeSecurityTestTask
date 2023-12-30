package com.example.homesecuritytesttask.controller

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homesecuritytesttask.data.DataResult
import com.example.homesecuritytesttask.data.repository.Repository
import com.example.homesecuritytesttask.data.repository.RepositoryImpl
import com.example.homesecuritytesttask.domain.Door
import kotlinx.coroutines.launch

class DoorScreenViewModel : ViewModel() {
    private val repository: Repository = RepositoryImpl()
    private val _doors: MutableLiveData<List<Door>> = MutableLiveData()
    val doors: LiveData<List<Door>> = _doors

    init {
        getDoors()
    }

    private fun getDoors() {
        viewModelScope.launch {
            when (val doors = repository.getDoors()) {
                is DataResult.Success -> _doors.value = doors.response
                is DataResult.Error -> Log.d("TAG", "Can't find any doors")
            }
        }
    }
}