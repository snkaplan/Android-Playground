package com.example.composeapp.rowandcolumn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composeapp.ui.theme.ComposeAppTheme

@Composable
fun RowAndColumnExample() {
    ComposeAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier
                    .height(500.dp)
                    .width(500.dp)
                    .background(Color.LightGray),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomColumnItem(weight = 3f)
                CustomColumnItem(weight = 1f, color = MaterialTheme.colors.secondary)
            }
        }
    }
}

@Composable
fun ColumnScope.CustomColumnItem(weight: Float, color: Color = MaterialTheme.colors.primary) {
    Surface(
        modifier = Modifier
            .width(200.dp)
            .weight(weight),
        color = color
    ) {
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            CustomRowItem(1f)
            CustomRowItem(2f, color = MaterialTheme.colors.secondaryVariant)
        }
    }
}

@Composable
fun RowScope.CustomRowItem(weight: Float, color: Color = MaterialTheme.colors.error) {
    Surface(
        modifier = Modifier
            .height(50.dp)
            .weight(weight),
        color = color
    ) {}
}