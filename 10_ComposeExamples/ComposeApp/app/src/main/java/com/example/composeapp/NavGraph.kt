package com.example.composeapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composeapp.boxexample.BoxExample
import com.example.composeapp.circularprogressbar.CircularProgressBar
import com.example.composeapp.circularprogressbar.CircularProgressBarItem
import com.example.composeapp.constraintlayout.ConstraintLayoutExample
import com.example.composeapp.expandablecard.ExpandableCard
import com.example.composeapp.expandablecard.ExpandableCardItem
import com.example.composeapp.imagecard.ImageCardExample
import com.example.composeapp.imagecard.ImageCardItem
import com.example.composeapp.lists.ListsExample
import com.example.composeapp.modifiers.ModifiersExample
import com.example.composeapp.navigation.Navigation
import com.example.composeapp.rowandcolumn.RowAndColumnExample
import com.example.composeapp.selectabletext.SelectableText
import com.example.composeapp.simpleanimations.SimpleAnimations
import com.example.composeapp.state.StateExample
import com.example.composeapp.supersubscript.SuperScriptSubScript
import com.example.composeapp.textcustomization.TextCustomization
import com.example.composeapp.textfield_buttons_snackbars.TBSExample
import com.google.gson.Gson

@Composable
fun NavGraph(
    startDestination: String = NavScreen.ExamplesScreen.title,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = NavScreen.ExamplesScreen.title) {
            ExamplesScreen(navController = navController)
        }
        composable(route = NavScreen.BoxExampleScreen.title) {
            BoxExample()
        }
        composable(route = NavScreen.ConstraintLayoutExampleScreen.title) {
            ConstraintLayoutExample()
        }
        composable(route = NavScreen.ExpandableCardExampleScreen.title + "/{data}",
            arguments = listOf(
                navArgument("data") { type = ExpandableCardItemNavArgType() }
            )
        ) { navBackStackEntry ->
            val data = navBackStackEntry.arguments?.getString("data")?.let {
                Gson().fromJson(it, ExpandableCardItem::class.java)
            }
            data?.let {
                ExpandableCard(it)
            }
        }


        composable(
            route = NavScreen.ImageCardExampleScreen.title + "/{data}", arguments = listOf(
                navArgument("data") { type = ImageCardItemNavArgType() })
        ) { navBackStackEntry ->
            val data = navBackStackEntry.arguments?.getString("data")?.let {
                Gson().fromJson(it, ImageCardItem::class.java)
            }
            data?.let {
                ImageCardExample(it)
            }
        }

        composable(route = NavScreen.ListsExampleScreen.title) {
            ListsExample()
        }
        composable(route = NavScreen.ModifiersExampleScreen.title) {
            ModifiersExample()
        }
        composable(route = NavScreen.NavigationExampleScreen.title) {
            Navigation()
        }
        composable(route = NavScreen.RowAndColumnExampleScreen.title) {
            RowAndColumnExample()
        }
        composable(route = NavScreen.SelectableTextExampleScreen.title) {
            SelectableText()
        }
        composable(route = NavScreen.StateExampleScreen.title) {
            StateExample()
        }
        composable(route = NavScreen.SuperSubScriptExampleScreen.title) {
            SuperScriptSubScript()
        }
        composable(route = NavScreen.TextCustomizationExampleScreen.title) {
            TextCustomization()
        }
        composable(route = NavScreen.TextButtonSnackbarExampleScreen.title) {
            TBSExample()
        }
        composable(route = NavScreen.SimpleAnimationsExampleScreen.title) {
            SimpleAnimations()
        }
        composable(route = NavScreen.CircularProgressBarExampleScreen.title + "/{data}",
            arguments = listOf(
                navArgument("data") { type = CircularProgressBarItemNavArgType() }
            )
        ) { navBackStackEntry ->
            val data = navBackStackEntry.arguments?.getString("data")?.let {
                Gson().fromJson(it, CircularProgressBarItem::class.java)
            }
            data?.let {
                CircularProgressBar(it)
            }
        }
    }
}