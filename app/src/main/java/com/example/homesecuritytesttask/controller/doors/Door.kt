package com.example.homesecuritytesttask.controller.doors

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.homesecuritytesttask.domain.Door
import org.koin.androidx.compose.koinViewModel
import kotlin.math.roundToInt

@Composable
fun DoorItem(door: Door) {
    val viewModel = koinViewModel<DoorScreenViewModel>()
    var isEditActive by rememberSaveable { mutableStateOf(false) }

    Column {
        if (isEditActive) {
            ChangeDoorNameEditText(
                door,
                changeIsActive = { isEditActive = false },
                updateName = { newName: String ->
                    isEditActive = false
                    door.name = newName
                    viewModel.updateDoor(door)
                })
        }
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
                                isEditActive = true
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
}

@Composable
fun Door(door: Door, modifier: Modifier) {
    val offsetX = rememberSaveable { mutableStateOf(0f) }
    Card(
        modifier = Modifier
            .fillMaxSize()
            .offset { IntOffset(x = offsetX.value.roundToInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    val consumed = offsetX.value + dragAmount
                    offsetX.value = consumed.coerceIn(-300f, 0f)
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = door.name,
                modifier = Modifier.padding(24.dp),
                fontSize = 17.sp,
                color = colorResource(
                    id = R.color.sub_title
                )
            )
            Row {
                if (door.favorites) {
                    Icon(
                        painter = painterResource(id = R.drawable.star_filled),
                        contentDescription = "favorite",
                        tint = colorResource(id = R.color.yellow)
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.lockon),
                    contentDescription = "favorite",
                    tint = colorResource(id = R.color.blue),
                    modifier = Modifier.padding(end = 16.dp, start = 8.dp)
                )
            }
        }
    }
}