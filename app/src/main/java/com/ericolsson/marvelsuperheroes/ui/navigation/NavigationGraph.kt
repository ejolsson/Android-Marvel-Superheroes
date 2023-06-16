package com.ericolsson.marvelsuperheroes.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ericolsson.marvelsuperheroes.domain.SuperHero
import com.ericolsson.marvelsuperheroes.ui.heroes.SuperHeroListScreen
import com.ericolsson.marvelsuperheroes.ui.navigation.Screens

@Composable
fun NavigationGraph(viewModel: HeroViewModel) {

    var hero: SuperHero? = null

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.SuperHeroListScreen.route) {
        composable(Screens.SuperHeroListScreen.route) {
            SuperHeroListScreen(viewModel = viewModel) {
                navController.navigate(Screens.SuperHeroDetailScreen.route)
            }
        }
        composable(Screens.SuperHeroDetailScreen.route) {
            if (hero != null) {
                SuperHeroDetailScreen(viewModel = viewModel, hero)
            }
        }
    }
}