package com.example.composeapp.circularprogressbar

import android.os.Parcelable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.SnackbarData
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Composable
fun CircularProgressBar(
    data: CircularProgressBarItem
) {
    var animationPlayed by remember { mutableStateOf(false) }

    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) data.percentage else 0f,
        animationSpec = tween(
            durationMillis = data.animDuration, delayMillis = data.animDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(data.radius * 2f)) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawArc(
                    color = data.color,
                    startAngle = -90f,
                    sweepAngle = 360 * curPercentage.value,
                    useCenter = false,
                    style = Stroke(data.strokeWidth.toPx(), cap = StrokeCap.Round)
                )
            }
            Text(
                text = (curPercentage.value * data.number).toInt().toString(),
                color = Color.Black,
                fontSize = data.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Parcelize
data class CircularProgressBarItem(
    val percentage: Float,
    val number: Int,
    val fontSize: @RawValue TextUnit = 28.sp,
    val radius: @RawValue Dp = 50.dp,
    val color: @RawValue Color = Color.Green,
    val strokeWidth: @RawValue Dp = 8.dp,
    val animDuration: Int = 1000,
    val animDelay: Int = 0
) : Parcelable