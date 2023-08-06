package com.example.composeapp.constraintlayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.composeapp.ui.theme.ComposeAppTheme
import kotlin.random.Random

@Composable
fun ConstraintLayoutExample() {
    val constraints = ConstraintSet {
        val greenBox = createRefFor("greenBox")
        val redBox = createRefFor("redBox")
        val guideline = createGuidelineFromTop(0.5f)

        constrain(greenBox) {
            top.linkTo(guideline)
            start.linkTo(parent.start)
            width = Dimension.percent(0.5f)
            height = Dimension.percent(0.2f)
        }
        constrain(redBox) {
            top.linkTo(parent.top)
            start.linkTo(greenBox.end)
            width = Dimension.percent(0.4f)
            height = Dimension.percent(0.5f)
        }
    }
    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .background(Color.Green)
            .layoutId("greenBox"))
        Box(modifier = Modifier
            .background(Color.Red)
            .layoutId("redBox"))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewOfConstraintLayoutExample() {
    ComposeAppTheme {
        // A surface container using the 'background' color from the theme
        Column(modifier = Modifier.fillMaxSize()) {
            ConstraintLayoutExample()
        }
    }
}