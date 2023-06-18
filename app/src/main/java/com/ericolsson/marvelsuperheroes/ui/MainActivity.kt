package com.ericolsson.marvelsuperheroes.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ericolsson.marvelsuperheroes.ui.heroes.MarvelSuperheroesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//class MainActivity @Inject constructor(private val viewModel: HeroViewModel): ComponentActivity() {

    private val viewModel: HeroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelSuperheroesTheme {
//                NavigationGraph(viewModel = viewModel)
                Surface(color = Color.DarkGray) {
                    NavigationGraph(viewModel = viewModel)
                }
            }
        }
    }
}



@Composable
fun Screen1(onClickListener: ()-> (Unit) ) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.DarkGray), contentAlignment = Alignment.Center) {
        Text(text = "Screen1", modifier = Modifier.clickable {
            onClickListener()
        })
    }
}