package com.example.homesecuritytesttask.controller.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.example.homesecuritytesttask.R
import com.example.homesecuritytesttask.controller.cameras.CameraScreen
import com.example.homesecuritytesttask.controller.doors.DoorScreen

@Composable
fun Tabs() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Камера", "Двери")

    Column {
        TabRow(selectedTabIndex = tabIndex, indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                color = colorResource(id = R.color.blue))
        }) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title, color = colorResource(id = R.color.title), fontSize = 17.sp) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> CameraScreen()
            1 -> DoorScreen()
        }
    }
}
