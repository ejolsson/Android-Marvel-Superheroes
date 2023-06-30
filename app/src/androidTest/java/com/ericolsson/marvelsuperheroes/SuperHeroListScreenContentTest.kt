package com.ericolsson.marvelsuperheroes

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.ericolsson.marvelsuperheroes.ui.MainActivity
import com.ericolsson.marvelsuperheroes.ui.heroes.SuperHeroListScreenContent
import com.ericolsson.marvelsuperheroes.ui.heroes.heroesSample
import org.junit.Rule
import org.junit.Test

class SuperHeroListScreenContentTest {

    @get:Rule
    val composeRule = createComposeRule()

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    val onHeroClick2: (Long) -> Unit = {}

    @Test // passed
    fun given_screen_when_launched_then_Details_displayed () {
        with(composeRule) {

            // Given
            setContent {
                SuperHeroListScreenContent(
                    heroes = heroesSample,
                    favs = 0,
                    onHeroClick2 = onHeroClick2,
                )
            }
            // When

            // Then
            onNodeWithText("Marvel Superheroes").assertIsDisplayed()
        }
    }
}

/*
    SuperHeroListScreenContent

        SuperHeroItem - heros load
 */