package com.ericolsson.marvelsuperheroes.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ericolsson.marvelsuperheroes.R
import com.ericolsson.marvelsuperheroes.SeriesRemote
import com.ericolsson.marvelsuperheroes.data.local.SuperHeroDetailLocal
import com.ericolsson.marvelsuperheroes.data.local.SuperHeroLocal
import com.ericolsson.marvelsuperheroes.data.mappers.ComicsRemoteToPresentationMapper
import com.ericolsson.marvelsuperheroes.data.mappers.SeriesRemoteToPresentationMapper
import com.ericolsson.marvelsuperheroes.data.remote.response.SuperHeroRemote
import com.ericolsson.marvelsuperheroes.data.repository.Repository
import com.ericolsson.marvelsuperheroes.domain.ComicsPresent
import com.ericolsson.marvelsuperheroes.domain.SeriesPresent
import com.ericolsson.marvelsuperheroes.domain.SuperHero
import com.ericolsson.marvelsuperheroes.domain.SuperHeroDetail
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val repository: Repository,
    private val seriesRemoteToPresentationMapper: SeriesRemoteToPresentationMapper,
    private val comicsRemoteToPresentationMapper: ComicsRemoteToPresentationMapper
    ): ViewModel() {

    // region state management
    private val apiKey = (R.string.marvel_api_key)

    // HeroList
    private val _heroListState = MutableStateFlow<List<SuperHero>>(emptyList())
    val heroListState: StateFlow<List<SuperHero>> get() = _heroListState

    // HeroDetail
    private var _heroState = MutableStateFlow<SuperHero>(heroSample)
    val heroState: StateFlow<SuperHero> get() = _heroState

    private val _seriesState = MutableStateFlow<List<SeriesPresent>>(emptyList())
    val seriesState: StateFlow<List<SeriesPresent>> get() = _seriesState

    private val _comicsState = MutableStateFlow<List<ComicsPresent>>(emptyList())
    val comicsState: StateFlow<List<ComicsPresent>> get() = _comicsState

    private val _favs = MutableStateFlow(0)
    val favs: StateFlow<Int> get() = _favs

    // endregion
    fun getHeroes5() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getHeroes4()
            }
            _heroListState.update { result } // point where api data gets assigned
            Log.w("Tag", "_heroListState: ${_heroListState.value}")
        }
    }

    fun getHeroByName5(id: Long) { // SuperHeroDetail
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getHeroByName4(id)
            }
            _heroState.update { result }
            Log.d("Tag", "_heroState: ${_heroState.value}")
        }
    }
    fun insertSuperhero(hero: SuperHero) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertHero(hero)
        }
    }
    fun getSeries5(heroId: Long) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getSeries4(heroId)
            }
            _seriesState.update { seriesRemoteToPresentationMapper.map(result) } // point where api data gets assigned
//            Log.d("Tag", "series = ${result.data.results.asList()}")
            Log.d("Tag", "_seriesState: ${_seriesState.value}")
        }
    }

    fun getComics5(heroId: Long) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getComics4(heroId)
            }
            _comicsState.update { comicsRemoteToPresentationMapper.map(result) }
            Log.d("Tag", "_comicsState: ${_comicsState.value}")
        }
    }

    // Complex method
    fun toggleFavorite(hero: SuperHero) {
        viewModelScope.launch {
            Log.w("Tag", "hero.fav before: ${hero.favorite}")
            val heroTemp = hero
            var hero = withContext(Dispatchers.Default) {
                val hero = heroTemp.copy(
                    favorite = !heroTemp.favorite
                ).also { superHero ->
                    if (heroTemp.favorite) {
                        repository.insertFav(superHero)
                    } else {
                        repository.deleteFav(superHero)
                    }
                }
                hero
                Log.w("Tag", "hero.fav after: ${hero.favorite}")
            }
        }
    }
    // Simple method
    fun toggleFav(hero: SuperHero) {
        viewModelScope.launch {
            Log.w("Tag", "hero.fav before: ${hero.favorite}")
            val hero = withContext(Dispatchers.Default) {
                hero.copy(favorite = !hero.favorite)
            }
            Log.w("Tag", "hero.fav after: ${hero.favorite}")
        }
    }


    // region fundamentals methods
    private var heroesPresent = listOf<SuperHeroRemote>()
    private var heroToShow: SuperHeroRemote? = null
    private var seriesToShow = listOf<SeriesRemote>()

    fun getHeroes() : List<SuperHeroRemote> {
        viewModelScope.launch(Dispatchers.IO) {
            Log.w("Tag", "apiKey = $apiKey")
            val client = OkHttpClient()
            val baseUrl = "https://gateway.marvel.com:443/v1/public"
            val url = "${baseUrl}/characters"
            val body = FormBody.Builder() // was FormBody.Builder()
                .add("ts", "1")
                .add("apikey", "resources")
                .add("hash", "a4d396a1143f5258c6cced5dc9863a84")
                .add("limit", "20")
                .add("offset", "800")
                .build()
            val request = Request.Builder()
                .url(url)
//                .addHeader("Authorization","Bearer $token")
                .get()
//                .put(body) // no workie
                .apply { body }
                .build()
            Log.d("Tag", "getHeroes request = $request")

//            val requestHard = "https://gateway.marvel.com/v1/public/characters?ts=1&apikey=f0c5210c2332d5d32edc3a40552edb27&hash=a4d396a1143f5258c6cced5dc9863a84&limit=3&offset=800"

            val urlHard = "https://gateway.marvel.com/v1/public/characters?ts=1&apikey=f0c5210c2332d5d32edc3a40552edb27&hash=a4d396a1143f5258c6cced5dc9863a84&limit=3&offset=800"
            val requestHard = Request.Builder()
                .url(urlHard)
                .get()
                .build()
            Log.d("Tag", "requestHard: $requestHard")

            val call = client.newCall(requestHard)
            Log.d("Tag", "call: $call")

            val response = call.execute()
            Log.d("Tag", "getHeroes Response: $response")

            if (response.isSuccessful) {
                response.body?.let { responseBody ->
                    val gson = Gson()
                    try {
                        val response = responseBody.string()
                        Log.d("Tag", "getHeroes try... response: $response") // prints good response
                        val getHeroesArray = gson.fromJson(response, SuperHeroRemote::class.java)
                        Log.d("Tag", "getHeroesArray.data.results.first().name = ${getHeroesArray.data.results.first().name}")
                    } catch (ex: Exception) {
//                        _mapState.value= MapState.ErrorJSON("Something went wrong in the fetchHeroes response")
                        Log.e("Tag", "Something went wrong in parsing Marvel response")
                    }
                } ?: run { Log.e("Tag", "Something went wrong with response.body?.let...") }
            }
            else {
                Log.e("Tag","response.isSuccessful == false")
            }

        }
        return heroesPresent
    }

    fun getHeroByName(heroName: String) : SuperHeroRemote? {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Tag", "apiKey = $apiKey")
            val client = OkHttpClient()
            val baseUrl = "https://gateway.marvel.com:443/v1/public"
            val url = "${baseUrl}/characters"
            val body = FormBody.Builder() // was FormBody.Builder()
                .add("ts", "1")
                .add("apikey", "resources")
                .add("hash", "a4d396a1143f5258c6cced5dc9863a84")
                .add("name", "hulk")
                .build()
            val request = Request.Builder()
                .url(url)
                .get()
                .apply { body }
                .build()
            Log.d("Tag", "getHeroByName request = $request")

            val urlHard = "https://gateway.marvel.com:443/v1/public/characters?ts=1&apikey=f0c5210c2332d5d32edc3a40552edb27&hash=a4d396a1143f5258c6cced5dc9863a84&name=$heroName"
            val requestHard = Request.Builder()
                .url(urlHard)
                .get()
                .build()
            Log.d("Tag", "val requestHard = $requestHard")

            val call = client.newCall(requestHard)
            Log.d("Tag", "getHeroByName call: $call")

            val response = call.execute()
            Log.d("Tag", "getHeroByName response: $response")

            if (response.isSuccessful) {
                response.body?.let { responseBody ->
                    val gson = Gson()
                    try {
                        val response = responseBody.string()
                        Log.d("Tag", "getHeroByName try... response: $response") // prints good response
                        val getHero = gson.fromJson(response, SuperHeroRemote::class.java)
                        Log.w("Tag", "getHeroByName.data.results = ${getHero.data.results.first()}")
                        Log.d("Tag", "getHero.data.results = ${getHero.data.results.first().name}, ${getHero.data.results.first().description}")
                    } catch (ex: Exception) {
//                        _mapState.value= MapState.ErrorJSON("Something went wrong in the fetchHeroes response")
                        Log.e("Tag", "Something went wrong in parsing Marvel response")
                    }
                } ?: run { Log.e("Tag", "Something went wrong with response.body?.let...") }
            }
            else {
                Log.e("Tag","response.isSuccessful == false")
            }

        }
        return heroToShow
    }

    fun getSeries(characterId: Int) : List<SeriesRemote> {
        viewModelScope.launch(Dispatchers.IO) {
            Log.w("Tag", "apiKey = $apiKey")
            val client = OkHttpClient()
            val baseUrl = "https://gateway.marvel.com:443/v1/public"
            val url = "${baseUrl}/characters"
            val body = FormBody.Builder() // was FormBody.Builder()
                .add("ts", "1")
                .add("apikey", "resources")
                .add("hash", "a4d396a1143f5258c6cced5dc9863a84")
                .add("name", "hulk")
                .build()
            val request = Request.Builder()
                .url(url)
                .get()
                .apply { body }
                .build()
            Log.d("Tag", "getHeroByName request = $request")

            val urlHard = "https://gateway.marvel.com:443/v1/public/characters/$characterId/series?ts=1&apikey=f0c5210c2332d5d32edc3a40552edb27&hash=a4d396a1143f5258c6cced5dc9863a84&limit=5&characterId=$characterId"
            val requestHard = Request.Builder()
                .url(urlHard)
                .get()
                .build()
            Log.d("Tag", "requestHard: $requestHard")

            val call = client.newCall(requestHard)
            Log.d("Tag", "call: $call")

            val response = call.execute()
            Log.d("Tag", "Response: $response")

            if (response.isSuccessful) {
                response.body?.let { responseBody ->
                    val gson = Gson()
                    try {
                        val response = responseBody.string()
                        Log.d("Tag", "try... response: $response")
                        val getSeriesArray = gson.fromJson(response, SeriesRemote::class.java)
                        Log.w("Tag", "getHero.data.results.first = ${getSeriesArray.data.results.first()}")
//                        Log.d("Tag", "getHero.data.results = ${getHero.data.results.first().name}, ${getHero.data.results.first().description}")
                    } catch (ex: Exception) {
//                        _mapState.value= MapState.ErrorJSON("Something went wrong in the fetchHeroes response")
                        Log.e("Tag", "Something went wrong in parsing Marvel response")
                    }
                } ?: run { Log.e("Tag", "Something went wrong with response.body?.let...") }
            }
            else {
                Log.e("Tag","response.isSuccessful == false")
            }

        }
        return seriesToShow
    }

    // endregion
}