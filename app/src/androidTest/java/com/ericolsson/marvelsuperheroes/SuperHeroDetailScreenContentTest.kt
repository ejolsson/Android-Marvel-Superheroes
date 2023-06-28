package com.ericolsson.marvelsuperheroes

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.ericolsson.marvelsuperheroes.data.local.SuperHeroLocal
import com.ericolsson.marvelsuperheroes.ui.SuperHeroDetailScreenContent
import com.ericolsson.marvelsuperheroes.ui.comicsSample
import com.ericolsson.marvelsuperheroes.ui.heroSample
import com.ericolsson.marvelsuperheroes.ui.seriesSample
import org.junit.Rule
import org.junit.Test

class SuperHeroDetailScreenContentTest {

    @get:Rule
    val composeRule = createComposeRule()
    val onFavClick: (SuperHeroLocal) -> Unit = {}

    @Test
    fun given_FavClick_When_False_Then_True() {
        with(composeRule) {

            // Given
            setContent {
                SuperHeroDetailScreenContent(
                    hero = heroSample,
                    series = seriesSample,
                    comics = comicsSample,
                    onFavClick3 = onFavClick
                )
            }
            // When

            // Then
            onNodeWithContentDescription("Favorite").assertExists()
            onNodeWithText("Details").assertIsDisplayed()
        }
    }
}