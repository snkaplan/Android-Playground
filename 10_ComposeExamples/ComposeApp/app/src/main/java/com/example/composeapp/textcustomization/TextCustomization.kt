package com.example.composeapp.textcustomization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.R
import com.example.composeapp.ui.theme.ComposeAppTheme

@Composable
fun TextCustomization() {
    Text(
        text = stringResource(id = R.string.app_name), modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .padding(16.dp),
        color = Color.White,
        fontSize = MaterialTheme.typography.h6.fontSize,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Composable
fun AnnotatedTextCustomization() {
    Text(
        buildAnnotatedString {
            withStyle(style = ParagraphStyle(textAlign = TextAlign.Center)) {
                withStyle(style = SpanStyle(color = Color.Blue, fontSize = 24.sp, fontWeight = FontWeight.ExtraLight)) {
                    append("A")
                }
                append("B")
                append("C")
                append("D")
            }
        },
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .padding(25.dp),
    )
}

@Composable
fun OverflowText() {
    Text(text = "Hello World".repeat(20), maxLines = 2, overflow = TextOverflow.Ellipsis)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewOfText() {
    ComposeAppTheme {
        // A surface container using the 'background' color from the theme
        Column(modifier = Modifier.fillMaxSize()) {
            //TextCustomization()
            //AnnotatedTextCustomization()
            AnnotatedTextCustomization()
        }
    }
}