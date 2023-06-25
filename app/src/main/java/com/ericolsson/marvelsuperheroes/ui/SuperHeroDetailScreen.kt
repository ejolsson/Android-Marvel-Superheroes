package com.ericolsson.marvelsuperheroes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ericolsson.marvelsuperheroes.data.local.SuperHeroLocal
import com.ericolsson.marvelsuperheroes.domain.ComicsPresent
import com.ericolsson.marvelsuperheroes.domain.SeriesPresent

@Composable
fun SuperHeroDetailScreen (viewModel: HeroViewModel, id: Long) {

    val heroState by viewModel.heroState.collectAsState()
    val seriesState by viewModel.seriesState.collectAsState()
    val comicsState by viewModel.comicsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getHeroByName5(id) // from local
        viewModel.getSeries5(id)
        viewModel.getComics5(id)
    }

    SuperHeroDetailScreenContent(hero = heroState, series = seriesState, comics = comicsState) {
        viewModel.toggleFavorite(heroState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroDetailScreenContent(
    hero: SuperHeroLocal,
    series: List<SeriesPresent>,
    comics: List<ComicsPresent>,
    onFavClick3: (SuperHeroLocal) -> Unit)
{

    val scaffoldS = rememberScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DetailTopBar(hero, onFavClick2 = onFavClick3)
        }
    ) { it ->
        LazyColumn(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp), contentPadding = it) {
            item {
                Text(
                    text = "Details",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            item {
                HeroDetailItem(hero = hero)
            }
            item {
                Text(
                    text = "Series",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            items(series, key = { it.id}) { series ->
                SeriesItem(series)
            }
            item {
                Text(
                    text = "Comics",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            items(comics, key = { it.id}) { comics ->
                ComicsItem(comics)
            }
        }
    }
}

// Content
@Composable
fun HeroDetailItem(hero: SuperHeroLocal, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp)
    ) {
        AsyncImage(
            model = hero.photo,
            contentDescription = hero.description,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentScale = ContentScale.Crop
        )
        Text(text = hero.description.toString(), style = typography.headlineSmall, modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun SeriesItem(series: SeriesPresent, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp)
    ) {
        Text(text = series.title, style = typography.headlineMedium, modifier = Modifier.padding(8.dp))
        AsyncImage(
            model = series.photo,
            contentDescription = series.description,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentScale = ContentScale.Crop
        )
        Text(text = series.description.toString(), style = typography.headlineSmall, modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun ComicsItem(comics: ComicsPresent, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp)
    ) {
        Text(text = comics.title, style = typography.headlineMedium, modifier = Modifier.padding(8.dp))
        AsyncImage(
            model = comics.photo,
            contentDescription = comics.description,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentScale = ContentScale.Crop
        )
        Text(text = comics.description.toString(), style = typography.headlineSmall, modifier = Modifier.padding(8.dp))
    }
}

// TopBar
@Composable
fun DetailTopBar(hero: SuperHeroLocal, onFavClick2: (SuperHeroLocal) -> Unit) {

    TopAppBar(
        title = { androidx.compose.material.Text(
            text = hero.name, style = androidx.compose.material3.MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(8.dp)
        ) },
        actions = {
            FavoriteHeart(hero = hero, onFavClick1 = onFavClick2)
        }
    )
}

@Composable
fun FavoriteHeart(hero: SuperHeroLocal, modifier: Modifier = Modifier, onFavClick1: (SuperHeroLocal) -> Unit) {

    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
                onFavClick1(hero) 
        }
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Favorite",
            tint = if (hero.favorite) Color.Yellow else Color.LightGray,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SuperHeroDetailScreen_Preview() {
    val onFavClick: (SuperHeroLocal) -> Unit = {}
    SuperHeroDetailScreenContent(heroSample, seriesSample, comicsSample, onFavClick)
}

@Preview
@Composable // done
fun DetailTopBar_Preview() {
    val onFavClick: (SuperHeroLocal) -> Unit = {}
    DetailTopBar(heroSample, onFavClick)
}

val heroSample = SuperHeroLocal(
    id = 1009664,
    name = "Thor",
    photo = "http://i.annihil.us/u/prod/marvel/i/mg/d/d0/5269657a74350.jpg",
    description = "God of lightening",
    favorite = true
)
val seriesSample = listOf(
    SeriesPresent(
        id = 16450,
        title = "A+X (2012 - 2014)",
        description = "Short description",
        photo = "http://i.annihil.us/u/prod/marvel/i/mg/5/d0/511e88a20ae34.jpg",
    )
)

val comicsSample = listOf(
    ComicsPresent(
        id = 5195,
        title = "Captain America: Winter Soldier Vol. 2",
        description = "The questions plaguing Captain America's dreams..",
        photo = "http://i.annihil.us/u/prod/marvel/i/mg/e/60/4bc60f02bc3bd.jpg",
    ),
    ComicsPresent(
        id = 3512,
        title = "HOUSE OF M: WORLD OF M FEATURING WOLVERINE TPB",
        description = "Explore the people and places of the World of M!",
        photo = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/646d0d81b6341.jpg",
    )
)