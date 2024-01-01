package com.example.homesecuritytesttask.controller.cameras

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homesecuritytesttask.controller.components.ProgressIndicator
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("SuspiciousIndentation")
@Composable
fun CameraScreen() {
    val viewModel: CameraScreenViewModel = viewModel()
    val cameras by viewModel.cameras.observeAsState(initial = listOf())
    val isRefreshing by viewModel.isRefreshing.observeAsState(false)
    val isLoading by viewModel.isLoading.observeAsState(false)

    Column(
        modifier = Modifier
            .padding(18.dp)
            .fillMaxSize()
    ) {
        if(isLoading) {
            ProgressIndicator()
        }

        Text(text = "Гостиная", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.refresh() },
        ) {
            LazyColumn {
                items(cameras) { camera ->
                    CameraItem(camera = camera)
                }
            }
        }
    }
}