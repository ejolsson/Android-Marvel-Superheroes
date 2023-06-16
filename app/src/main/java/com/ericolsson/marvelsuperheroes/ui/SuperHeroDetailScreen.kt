package com.ericolsson.marvelsuperheroes.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ericolsson.marvelsuperheroes.ui.heroes.MarvelSuperheroesTheme

@Composable
fun SuperHeroDetailScreen (viewModel: HeroViewModel) {

//    val state by viewModel.state

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

//    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        MarvelSuperheroesTheme {
            Greeting("Android")
        }
    }
}