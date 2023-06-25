package com.ericolsson.marvelsuperheroes.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import com.ericolsson.marvelsuperheroes.ui.heroes.MarvelSuperheroesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HeroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelSuperheroesTheme {
                Surface(color = Color.DarkGray) {
                    NavigationGraph(viewModel = viewModel)
                }
            }
        }
    }
}