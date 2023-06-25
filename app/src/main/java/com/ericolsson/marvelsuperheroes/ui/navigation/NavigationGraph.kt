package com.ericolsson.marvelsuperheroes.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ericolsson.marvelsuperheroes.ui.heroes.SuperHeroListScreen
import com.ericolsson.marvelsuperheroes.ui.navigation.Screens

@Composable
fun NavigationGraph(viewModel: HeroViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.SuperHeroListScreen.route) {
        composable(Screens.SuperHeroListScreen.route) {
            SuperHeroListScreen(viewModel = viewModel) { id: Long ->
                navController.navigate(Screens.SuperHeroDetailScreen.createRouteWithArgs(id = id))
            }
        }
        composable(
            Screens.SuperHeroDetailScreen.route,
            arguments = listOf(
                navArgument(Screens.SuperHeroDetailScreen.ARG_ID) {
                    type = NavType.LongType // was StringType
                }
            )
        ) {
            Log.d("Tag","NavigationGraph > NavHost > composable > Screens.SuperHeroDetailScreen.route")
            /**
             * id to receive it's value from the called for screen id "ARG_ID
             */
            val id = it.arguments?.getLong(Screens.SuperHeroDetailScreen.ARG_ID)
            val onFavClick: (Boolean) -> Unit = {}
            if (id != null) {
                // actually call the detail screen
                SuperHeroDetailScreen(viewModel = viewModel, id)
            } else {
                navController.navigateUp()
            }
        }
    }
}