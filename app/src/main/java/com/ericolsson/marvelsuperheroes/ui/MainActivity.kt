package com.ericolsson.marvelsuperheroes.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ericolsson.marvelsuperheroes.ui.heroes.MarvelSuperheroesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//class MainActivity @Inject constructor(private val viewModel: HeroViewModel): ComponentActivity() {

    private val viewModel: HeroViewModel by viewModels()
    private val hero = "hulk"
    private val heroId: Long = 1010914

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelSuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
//                    viewModel.getHeroes()
//                    viewModel.getHeroByName(hero)
//                    viewModel.getSeries(heroId)
                    viewModel.getHeroes5()
                    viewModel.getSeries5(heroId)
                    viewModel.getComics5(heroId)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarvelSuperheroesTheme {
        Greeting("Android")
    }
}