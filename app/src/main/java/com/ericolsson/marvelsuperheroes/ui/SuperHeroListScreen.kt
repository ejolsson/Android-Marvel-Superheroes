package com.ericolsson.marvelsuperheroes.ui.heroes

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ericolsson.marvelsuperheroes.ui.HeroViewModel
import com.ericolsson.marvelsuperheroes.domain.SuperHero

@Composable // done
fun SuperHeroListScreen (viewModel: HeroViewModel, onHeroClick: (Long) -> Unit) { // was onHeroClick: (Long) -> Unit = { _ ->}

    val state by viewModel.state.collectAsState()
    val favs by viewModel.favs.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getHeroes5()
    }

    SuperHeroListScreenContent(state, favs) { hero ->
        onHeroClick(hero) // onHeroClick has no idea what id to use...
        Log.d("Tag", "$hero cell clicked") // good print
//        Log.d("Tag", "state: $state, favs: $favs") // state: hero data
//        viewModel.insertSuperhero(hero)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroListScreenContent(heroes: List<SuperHero>, favs: Int, onSuperHeroListClicked: (Long) -> Unit) {

    val scaffoldS = rememberScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopBar(favs)
        },
        bottomBar = {
            MyBottomBar()
        }
    ) {
        LazyColumn(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp), contentPadding = it) {
            items(heroes, key = { it.id }) { hero ->
                SuperHeroItem(hero = hero, onHeroClick = onSuperHeroListClicked)
//                Log.d("Tag", "LazyColumn > items > hero: $hero")
            }
        }
    }
}

@Composable // done
fun BottomBarItem(text: String, icon: ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(imageVector = icon, contentDescription = icon.name)
        Text(text = text)        
    }
}

@Preview // done
@Composable
fun BottomBarItem_Preview() {
    BottomBarItem(text = "Home", icon = Icons.Default.Home)
}

@Composable // done
fun MyBottomBar() {
    BottomAppBar() {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            BottomBarItem(text = "Home", icon = Icons.Default.Home)
            BottomBarItem(text = "Favs", icon = Icons.Default.Favorite)
        }
    }
}

@Preview // done
@Composable
fun MyBottomBar_Preview() {
    MyBottomBar()
}

@OptIn(ExperimentalMaterial3Api::class) // done
@Composable
fun MyTopBar(favs: Int = 0) {

    CenterAlignedTopAppBar(title = {
        Text(text = "Superheroes: $favs")
    })
}

@Preview
@Composable // done
fun MyTopBar_Preview() {
    MyTopBar()
}

@Composable // done
fun SuperHeroItem(hero: SuperHero, modifier: Modifier = Modifier, onHeroClick: (Long) -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .clickable { onHeroClick(hero.id) } // should pass hero
    ) {
        AsyncImage(
            model = hero.photo, // hardcode: heroDefault.thumbnail.path,
            contentDescription = hero.description,// heroDefault.name,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentScale = ContentScale.Crop
        )
        Text(text = hero.name, style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable // done, not used??
fun SuperHeroItem_Preview() {
    SuperHeroItem(SuperHero(123,"Thor","http://i.annihil.us/u/prod/marvel/i/mg/d/d0/5269657a74350.jpg", "God of Lightening...")) { }
}

@Preview(showBackground = true) // done
@Composable
fun SuperHeroListScreen_Preview() {
    SuperHeroListScreenContent(heroesSample,0) { }
}

val heroesSample = listOf(
    SuperHero(
        id = 1009664,
        name = "Thor",
        photo = "http://i.annihil.us/u/prod/marvel/i/mg/d/d0/5269657a74350.jpg",
        description = "God of lightning"
    ),
    SuperHero(
        id = 1009368,
        name = "Iron Man",
        photo = "http://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55.jpg",
        description = "Billionaire, genius, industrialist"
    ),
    SuperHero(
        id = 1009282,
        name = "Doctor Strange",
        photo = "http://i.annihil.us/u/prod/marvel/i/mg/5/f0/5261a85a501fe.jpg",
        description = "Genius magician"
    )
)