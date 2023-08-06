package com.example.composeapp.textfield_buttons_snackbars

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapp.ui.theme.ComposeAppTheme
import kotlinx.coroutines.launch

@Composable
fun TBSExample() {
    val scaffoldState = rememberScaffoldState()
    var textFieldState by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    Scaffold(modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 30.dp
                )
        ) {
            TextField(
                value = textFieldState,
                label = { Text(text = "Enter your name") },
                onValueChange = { textFieldState = it },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldState")
                }
            }) {
                Text(text = "Pls greet me")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewOfTBSExample() {
    ComposeAppTheme {
        // A surface container using the 'background' color from the theme
        Column(modifier = Modifier.fillMaxSize()) {
            TBSExample()
        }
    }
}