package com.example.homesecuritytesttask.controller

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CameraScreen() {
    val viewModel: CameraScreenViewModel = viewModel()
    val cameras by viewModel.cameras.observeAsState(initial = listOf())

    LazyColumn {
        items(cameras) {
                camera ->
            Text(text = "Camera Name: ${camera.name}")
        }
    }
}