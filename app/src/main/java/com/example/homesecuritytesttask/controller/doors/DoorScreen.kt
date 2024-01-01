package com.example.homesecuritytesttask.controller.doors

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homesecuritytesttask.controller.components.ProgressIndicator
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.koinViewModel

@Composable
fun DoorScreen() {
    val viewModel = koinViewModel<DoorScreenViewModel>()
    val doors by viewModel.doors.observeAsState(initial = listOf())
    val isRefreshing by viewModel.isRefreshing.observeAsState(false)
    val isLoading by viewModel.isLoading.observeAsState(false)

    Column(
        modifier = Modifier
            .padding(18.dp)
            .fillMaxSize()
    ) {
        if (isLoading) {
            ProgressIndicator()
        }
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.refresh() },
        ) {
            LazyColumn {
                items(doors) { door ->
                    DoorItem(door = door)
                }
            }
        }
    }
}

