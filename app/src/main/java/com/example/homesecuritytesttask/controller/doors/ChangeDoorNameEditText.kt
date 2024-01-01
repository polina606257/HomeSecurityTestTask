package com.example.homesecuritytesttask.controller.doors

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.homesecuritytesttask.domain.Door

@Composable
fun ChangeDoorNameEditText(
    door: Door,
    changeIsActive: () -> Unit,
    updateName: (newName: String) -> Unit
) {
    var newName by rememberSaveable { mutableStateOf(door.name) }

    Dialog(
        onDismissRequest = changeIsActive
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            TextField(
                value = newName,
                onValueChange = { newName = it },
                label = { Text("Enter new name") },
                textStyle = TextStyle(color = Color.Black),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { updateName(newName) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Save")
            }
        }
    }
}