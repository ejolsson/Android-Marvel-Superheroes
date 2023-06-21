package com.ericolsson.marvelsuperheroes.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
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
import com.ericolsson.marvelsuperheroes.domain.ComicsPresent
import com.ericolsson.marvelsuperheroes.domain.SeriesPresent
import com.ericolsson.marvelsuperheroes.domain.SuperHero

@Composable
fun SuperHeroDetailScreen (viewModel: HeroViewModel, id: Long, onFavClick4: (Boolean) -> Unit) {

    val heroState by viewModel.heroState.collectAsState()
    val seriesState by viewModel.seriesState.collectAsState()
    val comicsState by viewModel.comicsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getHeroByName5(id) // from local
        viewModel.getSeries5(id)
        viewModel.getComics5(id)
    }

    SuperHeroDetailScreenContent(hero = heroState, series = seriesState, comics = comicsState, onFavClick3 = onFavClick4)
//    SuperHeroDetailScreenContentSample()

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroDetailScreenContent(hero: SuperHero, series: List<SeriesPresent>, comics: List<ComicsPresent>, onFavClick3: (Boolean) -> Unit) {

    val scaffoldS = rememberScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DetailTopBar(hero, onFavClick2 = onFavClick3)
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
fun HeroDetailItem(hero: SuperHero, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp)
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
@OptIn(ExperimentalMaterial3Api::class) // done
@Composable
fun DetailTopBar(hero: SuperHero, onFavClick2: (Boolean) -> Unit) { // Lvl 2, child to _

//    TopAppBar(title = { // was CenterAlignedTopAppBar
//        Row (horizontalArrangement =
//
//                ){
//
//        }
//        Text(text = "$heroName", style = typography.headlineLarge)
//        FavoriteHeart()
//    })

    androidx.compose.material.TopAppBar(
        title = { androidx.compose.material.Text(
            text = hero.name, style = androidx.compose.material3.MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(8.dp)
        ) },
//        navigationIcon = {
//            val onBackClick
//            IconButton(onClick = onBackClick) {
//                androidx.compose.material.Icon(
//                    imageVector = Icons.Default.KeyboardArrowLeft,
//                    contentDescription = "Back"
//                )
//            }
//        },
        actions = {
            FavoriteHeart(hero = hero, onFavClick1 = onFavClick2)
        }
    )

}

@Preview
@Composable // done
fun DetailTopBar_Preview() {
    val onFavClick: (Boolean) -> Unit = {}
    DetailTopBar(heroSample, onFavClick)
}

@Composable
fun FavoriteHeart(hero: SuperHero, onFavClick1: (Boolean) -> Unit) { // Lvl 1, child to DetailTopBar

//    val isFavorite by remember { mutableStateOf(false) } // not used..?

    Row (
        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.clickable { isFavorite = !isFavorite}
        modifier = Modifier.clickable {
            Log.w("Tag", "onFavClick1, ${hero.name} fav status: ${hero.favorite}")
            onFavClick1(hero.favorite)} // todo: add toggle logic
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Favorite",
//            tint = if (isFavorite) MaterialTheme.colors.primary else Color.LightGray,
            tint = if (hero.favorite) Color.Yellow else Color.LightGray,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = if (hero.favorite) "Favorite" else "Not favorite")
    }
}

//@Preview
//@Composable
//fun FavoriteHeart_Preview(hero: SuperHero, onFavClick2: (Boolean) -> Unit) {
//    FavoriteHeart(heroSample, true)
//}

@Preview(showBackground = true)
@Composable
fun SuperHeroDetailScreen_Preview() {
    val onFavClick: (Boolean) -> Unit = {}
    SuperHeroDetailScreenContent(heroSample, seriesSample, comicsSample, onFavClick)
}

val heroSample = SuperHero(
    id = 1009664,
    name = "Thor",
    photo = "\"http://i.annihil.us/u/prod/marvel/i/mg/d/d0/5269657a74350.jpg",
    description = "God of lightening",
    favorite = true
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
