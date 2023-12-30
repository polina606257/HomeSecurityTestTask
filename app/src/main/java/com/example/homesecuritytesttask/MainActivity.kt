package com.example.homesecuritytesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.homesecuritytesttask.ui.theme.HomeSecurityTestTaskTheme
import android.graphics.Color.parseColor
import androidx.compose.ui.unit.sp
import com.example.homesecuritytesttask.controller.CameraScreen
import com.example.homesecuritytesttask.controller.DoorScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeSecurityTestTaskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeSecurityScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSecurityScreen() {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Mой дом",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            },
        )
        Tabs()
    }
}

@Composable
fun Tabs() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Камера", "Двери")

    Column {
        TabRow(selectedTabIndex = tabIndex, indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                color = Color(parseColor("#add8e6"))
            )
        }) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title, color = Color.Black) },
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HomeSecurityTestTaskTheme {
        HomeSecurityScreen()
    }
}