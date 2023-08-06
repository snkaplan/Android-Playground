package com.example.composeapp.expandablecard

import android.os.Parcelable
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composeapp.ui.theme.Shapes
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Composable
fun ExpandableCard(data: ExpandableCardItem) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            ),
        shape = Shapes.medium,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(data.padding)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.weight(6f),
                    text = data.title,
                    fontWeight = data.titleFontWeight,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .weight(1f)
                        .rotate(rotationState),
                    onClick = { expandedState = expandedState.not() }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "DropDownArrow")
                }
            }
            if (expandedState) {
                Text(
                    text = data.description,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = data.descriptionFontWeight,
                    maxLines = data.descriptionMaxLine,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Parcelize
data class ExpandableCardItem(
    val title: String,
    val titleFontWeight: @RawValue FontWeight = FontWeight.Bold,
    val description: String,
    val descriptionFontWeight: @RawValue FontWeight = FontWeight.Normal,
    val descriptionMaxLine: Int,
    val padding: @RawValue Dp = 12.dp
) : Parcelable

@Preview(showBackground = true)
@Composable
fun DefaultPreviewOfExpandableCard() {
    ExpandableCard(ExpandableCardItem(title = "My Title", description = "Hello World!".repeat(10), descriptionMaxLine = 4))
}