package com.example.homesecuritytesttask.controller.cameras

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.homesecuritytesttask.R
import com.example.homesecuritytesttask.domain.Camera
import org.koin.androidx.compose.koinViewModel
import kotlin.math.roundToInt

@Composable
fun Camera(camera: Camera, modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<CameraScreenViewModel>()
    val offsetX = rememberSaveable { mutableStateOf(0f) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Card(
            modifier
                .padding(vertical = 4.dp)
                .fillMaxSize()
                .offset { IntOffset(x = offsetX.value.roundToInt(), 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { change, dragAmount ->
                        val consumed = offsetX.value + dragAmount
                        offsetX.value = consumed.coerceIn(-150f, 0f)
                    }
                },
            shape = RoundedCornerShape(12.dp),
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
                        painter = painterResource(id = R.drawable.rec),
                        contentDescription = "REC",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(25.dp)
                            .offset(8.dp, 8.dp),
                        tint = Color.Red
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    if (camera.favorites) {
                        Icon(
                            painter = painterResource(id = R.drawable.star_filled),
                            contentDescription = "favorite",
                            modifier = Modifier
                                .padding(8.dp)
                                .offset(-8.dp, 8.dp),
                            tint = Color.Yellow
                        )
                    }
                }
            }
            Text(
                text = camera.name,
                modifier = Modifier.padding(24.dp),
                fontSize = 17.sp,
                color = colorResource(
                    id = R.color.sub_title
                )
            )
        }
        // change favorite icon
        if (offsetX.value < -100f) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .border(1.dp, Color.LightGray, CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "favorite",
                    tint = colorResource(id = R.color.yellow),
                    modifier = Modifier
                        .size(30.dp)
                        .clickable(onClick = {
                            camera.favorites = !camera.favorites
                            viewModel.updateCamera(camera)
                            offsetX.value = 0.0F
                        })
                )
            }
        }
    }
}