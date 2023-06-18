package com.ericolsson.marvelsuperheroes.ui.navigation

import android.util.Log
import com.ericolsson.marvelsuperheroes.ui.navigation.Screens.SuperHeroDetailScreen.ARG_ID

/**
 * This file essentially provides a schema for strings that outline screens for the navigation graph
 */
sealed class Screens (val route: String) {
    object SuperHeroListScreen: Screens(SCREEN1_BASE_ROUTE)
    object SuperHeroDetailScreen: Screens(SCREEN2_ROUTE_TEMPLATE) {
        // this object is of type Screens(SuperHeroDetailScreen/heroId)

        const val ARG_ID = "heroId"
        fun createRouteWithArgs(id: Long): String { // called in NavGraph
            Log.d("Tag", "Hi fm createRouteWithArgs, id: $id") // correct print w id
            Log.w("Tag", "SCREEN2_ROUTE_TO_FORMAT.format(id): ${SCREEN2_ROUTE_TO_FORMAT.format(id)}")
            return SCREEN2_ROUTE_TO_FORMAT.format(id) // returns SuperHeroDetailScreen/1009735
        }
    }

    companion object { // just a bunch of strings
        private const val SCREEN1_BASE_ROUTE = "SuperHeroListScreen"
        private const val SCREEN2_BASE_ROUTE = "SuperHeroDetailScreen"
        private const val SCREEN2_ROUTE_TEMPLATE = "$SCREEN2_BASE_ROUTE/{$ARG_ID}"
        // SuperHeroDetailScreen/heroId
        private const val SCREEN2_ROUTE_TO_FORMAT = "$SCREEN2_BASE_ROUTE/%s"
    }
}