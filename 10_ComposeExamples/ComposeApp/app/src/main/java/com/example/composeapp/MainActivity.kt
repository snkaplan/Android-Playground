package com.example.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composeapp.expandablecard.ExpandableCard
import com.example.composeapp.navigation.Navigation
import com.example.composeapp.navigation.Screen
import com.example.composeapp.ui.theme.ComposeAppTheme
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavGraph()
        }
    }
}

@Composable
fun ExamplesScreen(navController: NavController) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(NavScreen::class.sealedSubclasses) { index, item ->
            val current = (item.objectInstance as NavScreen)
            ExampleItem(current.title) {
                navController.navigate(current.withArgs())
            }
        }
    }
}

@Composable
fun ExampleItem(
    title: String, onClick: () -> Unit
) {
    Button(onClick = {
        onClick.invoke()
    }, modifier = Modifier.fillMaxWidth()) {
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAppTheme {
        // A surface container using the 'background' color from the theme
        Column(modifier = Modifier.fillMaxSize()) {
            Navigation()
        }
    }
}