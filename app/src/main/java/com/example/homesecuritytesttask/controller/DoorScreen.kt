package com.example.homesecuritytesttask.controller

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DoorScreen() {
    val viewModel: DoorScreenViewModel = viewModel()
    val doors by viewModel.doors.observeAsState(initial = listOf())

    LazyColumn {
        items(doors) {
                door ->
            Text(text = "Door Name: ${door.name}")
        }
    }
}