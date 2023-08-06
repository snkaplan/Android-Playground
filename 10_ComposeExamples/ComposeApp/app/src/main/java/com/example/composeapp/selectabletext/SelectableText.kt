package com.example.composeapp.selectabletext

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeapp.R
import com.example.composeapp.ui.theme.ComposeAppTheme

@Composable
fun SelectableText() {
    SelectionContainer {
        Column {
            Text(
                text = stringResource(id = R.string.app_name)
            )
            DisableSelection {
                Text(
                    text = stringResource(id = R.string.app_name)
                )
            }
            Text(
                text = stringResource(id = R.string.app_name)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewOfSelectableText() {
    ComposeAppTheme {
        // A surface container using the 'background' color from the theme
        Column(modifier = Modifier.fillMaxSize()) {
            SelectableText()
        }
    }
}