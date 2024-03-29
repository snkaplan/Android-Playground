package com.example.composeapp.imagecard

import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.ui.theme.Shapes
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Composable
fun ImageCardExample(
    data: ImageCardItem,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(16.dp)
    ) {
        ImageCard(
            painter = painterResource(id = com.example.composeapp.R.drawable.kermit),
            contentDescription = data.contentDescription,
            title = data.title,
            modifier = modifier
        )
    }
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp), elevation = 5.dp) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(painter = painter, contentDescription = contentDescription, contentScale = ContentScale.Crop)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black), startY = 300f))
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp), contentAlignment = Alignment.BottomStart
            ) {
                Text(text = title, style = TextStyle(color = Color.White, fontSize = 16.sp))
            }
        }
    }
}

@Parcelize
data class ImageCardItem(
    val contentDescription: String,
    val title: String,
) : Parcelable