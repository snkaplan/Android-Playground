package com.example.composeapp.modifiers

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ModifiersExample() {
    Column(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxHeight(0.5f)
            .fillMaxWidth()
            .padding(horizontal = 60.dp, vertical = 30.dp)
            .border(width = 2.dp, color = Color.Red)
            .padding(5.dp)
            .border(1.dp, Color.Magenta)
            .padding(10.dp)
            .border(3.dp, Color.Black)
        //.width(300.dp)
        //.requiredWidth(600.dp)
    ) {
        // Offset is similar to margin but it does not pushes other elements.
        Text(text = "Hello World", modifier = Modifier.offset(50.dp, 45.dp))
        // Spacer creates an empty composable and creates space
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Hello World 2")
    }
}