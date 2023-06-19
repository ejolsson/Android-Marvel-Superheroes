package com.ericolsson.marvelsuperheroes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ericolsson.marvelsuperheroes.domain.ComicsPresent
import com.ericolsson.marvelsuperheroes.domain.SeriesPresent
import com.ericolsson.marvelsuperheroes.domain.SuperHero
import com.ericolsson.marvelsuperheroes.ui.heroes.MyTopBar

@Composable
fun SuperHeroDetailScreen (viewModel: HeroViewModel, id: Long) { // was hero: SuperHero

    val heroState by viewModel.heroState.collectAsState()
    val seriesState by viewModel.seriesState.collectAsState()
    val comicsState by viewModel.comicsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getHeroByName5(id) // from local
        viewModel.getSeries5(id)
        viewModel.getComics5(id)
    }

    SuperHeroDetailScreenContent(hero = heroState, series = seriesState, comics = comicsState, fav = false)
//    SuperHeroDetailScreenContentSample()

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroDetailScreenContent(hero: SuperHero, series: List<SeriesPresent>, comics: List<ComicsPresent>, fav: Boolean) {

    val scaffoldS = rememberScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopBar("${hero.name}") // todo: get hero name
        }
    ) {
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
                heroDetailItem(hero = hero)
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

@Composable
fun SuperHeroDetailScreenContentSample() {
    Column(
        modifier = Modifier.padding(16.dp)
    )
    {
        Text(text = "Hero Name")
//    Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription ="hero picture")
        Text(text = "Description")
        Text(text = "SERIES")
        Text(text = "Series Title 1")
//    Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription ="Series picture")
        Text(text = "COMICS")
        Text(text = "Comics Title 1")
//    Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription ="Comics picture")
    }
}

@Composable
fun heroDetailItem(hero: SuperHero, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp)
//            .clickable { onHeroClick(series) }
    ) {
//        Text(text = hero.description, style = typography.headlineMedium, modifier = Modifier.padding(8.dp))
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
//            .clickable { onHeroClick(series) }
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
//            .clickable { onHeroClick(series) }
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

@OptIn(ExperimentalMaterial3Api::class) // done
@Composable
fun MyTopBar(heroName: String) {

    CenterAlignedTopAppBar(title = {
        Text(text = "$heroName", style = typography.headlineLarge)
    })
}

@Preview
@Composable // done
fun MyTopBar_Preview() {
    MyTopBar()
}


@Preview(showBackground = true)
@Composable
fun SuperHeroDetailScreen_Preview() {

    SuperHeroDetailScreenContent(heroSample, seriesSample, comicsSample, false)
}

val heroSample = SuperHero(
    id = 1009664,
    name = "Thor",
    photo = "\"http://i.annihil.us/u/prod/marvel/i/mg/d/d0/5269657a74350.jpg",
    description = "God of lightening"
)
val seriesSample = listOf(
    SeriesPresent(
        id = 16450,
        title = "A+X (2012 - 2014)",
        description = "Short description", // "et ready for action-packed stories featuring team-ups from your favorite Marvel heroes every month! First, a story where Wolverine and Hulk come together, and then Captain America and Cable meet up! But will each partner's combined strength be enough?"
        photo = "http://i.annihil.us/u/prod/marvel/i/mg/5/d0/511e88a20ae34.jpg",
    ),
//    SeriesPresent(
//        id = 6079,
//        title = "Adam: Legend of the Blue Marvel (2008)",
//        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
//        photo = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5449710ac36d2.jpg",
//    ),
//    SeriesPresent(
//        id = 27392,
//        title = "Aero (2019 - 2020)",
//        description = "",
//        photo = "http://i.annihil.us/u/prod/marvel/i/mg/3/00/5d128077da440.jpg",
//    )
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
