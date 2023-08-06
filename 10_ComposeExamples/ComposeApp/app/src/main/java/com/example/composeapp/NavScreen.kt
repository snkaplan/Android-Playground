package com.example.composeapp

import android.net.Uri
import com.example.composeapp.circularprogressbar.CircularProgressBarItem
import com.example.composeapp.expandablecard.ExpandableCardItem
import com.example.composeapp.imagecard.ImageCardExample
import com.example.composeapp.imagecard.ImageCardItem
import com.google.gson.Gson

sealed class NavScreen(val title: String, val argument: Any? = null) {
    object ExamplesScreen : NavScreen("ExamplesScreen")
    object BoxExampleScreen : NavScreen("BoxExampleScreen")
    object ConstraintLayoutExampleScreen : NavScreen("ConstraintLayoutExampleScreen")
    object ExpandableCardExampleScreen : NavScreen(
        "ExpandableCardExampleScreen",
        ExpandableCardItem(title = "My Title", description = "Hello World!".repeat(10), descriptionMaxLine = 4)
    )

    object ImageCardExampleScreen : NavScreen(
        "ImageCardExampleScreen",
        ImageCardItem(contentDescription = "Kermit in the snow", title = "Kermit is playing in the snow")
    )

    object ListsExampleScreen : NavScreen("ListsExampleScreen")
    object ModifiersExampleScreen : NavScreen("ModifiersExampleScreen")
    object NavigationExampleScreen : NavScreen("NavigationExampleScreen")
    object RowAndColumnExampleScreen : NavScreen("RowAndColumnExampleScreen")
    object SelectableTextExampleScreen : NavScreen("SelectableTextExampleScreen")
    object StateExampleScreen : NavScreen("StateExampleScreen")
    object SuperSubScriptExampleScreen : NavScreen("SuperSubScriptExampleScreen")
    object TextCustomizationExampleScreen : NavScreen("TextCustomizationExampleScreen")
    object TextButtonSnackbarExampleScreen : NavScreen("TextButtonSnackbarExampleScreen")
    object SimpleAnimationsExampleScreen : NavScreen("SimpleAnimationsExampleScreen")
    object CircularProgressBarExampleScreen : NavScreen(
        "CircularProgressBarExampleScreen",
        CircularProgressBarItem(percentage = 0.8f, number = 80)
    )

    fun withArgs(): String {
        return buildString {
            append(title)
            argument?.let {
                val json = Uri.encode(Gson().toJson(it))
                append("/$json")
            }
        }
    }
}
