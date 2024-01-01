package com.example.homesecuritytesttask.controller

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.homesecuritytesttask.R
import com.example.homesecuritytesttask.domain.Door
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlin.math.roundToInt

@Composable
fun DoorScreen() {
    val viewModel: DoorScreenViewModel = viewModel()
    val doors by viewModel.doors.observeAsState(initial = listOf())
    val isRefreshing by viewModel.isRefreshing.observeAsState(false)

    Column(
        modifier = Modifier
            .padding(18.dp)
            .fillMaxSize()
    ) {
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

@Composable
fun DoorItem(door: Door) {
    val viewModel:DoorScreenViewModel = viewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Door(door, modifier = Modifier.fillMaxSize())
        Row(modifier = Modifier.align(Alignment.CenterEnd)) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .border(1.dp, Color.LightGray, CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = "edit",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable(onClick = {
                            // Handle click event
                        }),
                    tint = colorResource(id = R.color.blue)
                )
            }
            Spacer(modifier = Modifier.padding(end = 8.dp))
            Box(
                modifier = Modifier
                    .border(1.dp, Color.LightGray, CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "favorite",
                    tint = colorResource(id = R.color.yellow),
                    modifier = Modifier
                        .size(30.dp)
                        .clickable(onClick = {
                            door.favorites = !door.favorites
                            viewModel.updateDoor(door)
                        })
                )
            }
        }
    }
}

@Composable
fun Door(door: Door, modifier: Modifier) {
    val offsetX = rememberSaveable { mutableStateOf(0f) }
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxSize()
            .offset { IntOffset(x = offsetX.value.roundToInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    val consumed = offsetX.value + dragAmount
                    offsetX.value = consumed.coerceIn(-380f, 0f)
                }
            },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        if (door.snapshot != null) {
            AsyncImage(
                model = door.snapshot,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 10f)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = door.name, modifier = Modifier.padding(24.dp))
            Row {
                if (door.favorites) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "favorite",
                        modifier = Modifier
                            .padding(8.dp)
                            .offset(-8.dp, 8.dp),
                        tint = Color.Yellow
                    )
                }
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "favorite",
                    modifier = Modifier
                        .padding(8.dp)
                        .offset(-8.dp, 8.dp),
                    tint = Color(android.graphics.Color.parseColor("#add8e6"))
                )
            }
        }
    }
}