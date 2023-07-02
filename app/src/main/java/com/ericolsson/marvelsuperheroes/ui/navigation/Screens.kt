package com.ericolsson.marvelsuperheroes.ui.navigation

import com.ericolsson.marvelsuperheroes.ui.navigation.Screens.SuperHeroDetailScreen.ARG_ID

/**
 * This file essentially provides a schema for 2 strings that outline screens for the navigation graph. 2 strings are:
 *
 * Screens.SuperHeroListScreen   a.k.a SuperHeroListScreen
 *
 * Screens.SuperHeroDetailScreen/123456   a.k.a SuperHeroDetailScreen/123456
 */
sealed class Screens (val route: String) {
    object SuperHeroListScreen: Screens(SCREEN1_BASE_ROUTE)
    object SuperHeroDetailScreen: Screens(SCREEN2_ROUTE_TEMPLATE) {
        // this object is of type Screens(SuperHeroDetailScreen/heroId)

        const val ARG_ID = "heroId"
        fun createRouteWithArgs(id: Long): String { // called in NavGraph L22
//            Log.w("Tag", "SCREEN2_ROUTE_TO_FORMAT.format(id): ${SCREEN2_ROUTE_TO_FORMAT.format(id)}")
            return SCREEN2_ROUTE_TO_FORMAT.format(id) // returns SuperHeroDetailScreen/1009735
        }
    }

    companion object { // just a bunch of strings
        private const val SCREEN1_BASE_ROUTE = "SuperHeroListScreen"
        private const val SCREEN2_BASE_ROUTE = "SuperHeroDetailScreen"
        private const val SCREEN2_ROUTE_TEMPLATE = "$SCREEN2_BASE_ROUTE/{$ARG_ID}"
        private const val SCREEN2_ROUTE_TO_FORMAT = "$SCREEN2_BASE_ROUTE/%s"
    }
}