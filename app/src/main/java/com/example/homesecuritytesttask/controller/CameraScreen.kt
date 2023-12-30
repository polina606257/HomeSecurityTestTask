package com.example.homesecuritytesttask.controller

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.homesecuritytesttask.R
import com.example.homesecuritytesttask.domain.Camera

@Composable
fun CameraScreen() {
    val viewModel: CameraScreenViewModel = viewModel()
    val cameras by viewModel.cameras.observeAsState(initial = listOf())

    Column(
        modifier = Modifier
            .padding(18.dp)
            .fillMaxSize()
    ) {
        Text(text = "Гостиная", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))
        LazyColumn {
            items(cameras) { camera ->
                Camera(camera)
            }
        }
    }
}

@Composable
fun Camera(camera: Camera) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Box {
            AsyncImage(
                model = camera.snapshot,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 10f)
            )
            if (camera.rec) {
                Icon(
                    painter = painterResource(id = R.drawable.videocam),
                    contentDescription = "REC",
                    modifier = Modifier
                        .padding(8.dp)
                        .offset(8.dp, 8.dp),
                    tint = Color.Red
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                if (camera.favorites) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "favorite",
                        modifier = Modifier
                            .padding(8.dp)
                            .offset(-8.dp, 8.dp),
                        tint = Color.Yellow
                    )
                }
            }
        }
        Text(text = camera.name, modifier = Modifier.padding(24.dp))
    }
}