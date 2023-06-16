package com.ericolsson.marvelsuperheroes.ui.navigation

sealed class Screens (val route: String) {
    object SuperHeroListScreen: Screens(SCREEN1_BASE_ROUTE)
    object SuperHeroDetailScreen: Screens(SCREEN2_BASE_ROUTE)

    companion object {
        private const val SCREEN1_BASE_ROUTE = "SuperHeroListScreen"
        private const val SCREEN2_BASE_ROUTE = "SuperHeroDetailScreen"
    }
}