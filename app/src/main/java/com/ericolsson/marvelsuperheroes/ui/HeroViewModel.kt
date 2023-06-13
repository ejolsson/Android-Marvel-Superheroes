package com.ericolsson.marvelsuperheroes.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ericolsson.marvelsuperheroes.R
import com.ericolsson.marvelsuperheroes.Result
import com.ericolsson.marvelsuperheroes.MarvelSeriesDTO
import com.ericolsson.marvelsuperheroes.SuperHeroRemote
import com.ericolsson.marvelsuperheroes.MarvelHeroesDTO
import com.ericolsson.marvelsuperheroes.data.repository.Repository
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
class HeroViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _heroesRemote = MutableLiveData<List<SuperHeroRemote>>()
    private var _heroes = MutableLiveData<MarvelHeroesDTO>()
    private val apiKey = (R.string.marvel_api_key)
    private val _state = MutableStateFlow<List<Result>>(emptyList())
    val state: StateFlow<List<Result>> get() = _state

//    public static final MARVEL_API_KEY: String = Reso

    /*
    var urlComponents = URLComponents()
        urlComponents.queryItems = [
            URLQueryItem(name: "ts", value: "1"),
            URLQueryItem(name: "apikey", value: "f0c5210c2332d5d32edc3a40552edb27"),
            URLQueryItem(name: "hash", value: "a4d396a1143f5258c6cced5dc9863a84"),
            URLQueryItem(name: "limit", value: "1"),
            URLQueryItem(name: "offset", value: "200")]
        // prints: ?ts=1&apikey=f0...&hash=a4d....a84&limit=1&offset=200
     */

    fun getHeroes5() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getHeroes4() // type MarvelHeroesDTO
            }
//            _heroes.value?.data.results = result.data.results // Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type Data?
            Log.d("Tag getHeroes5", "HeroVM > fun getHeroes5 > result.data.results.first() = ${result.data.results.first()}") // prints first hero correctly

            val heroes2 = result.data.results.asList() //get("Result") as JsonArray
            Log.w("Tag", "heroes2 = $heroes2")
//            _heroesRemote.value = heroes2 // Type mismatch. Req: List<SuperHeroRemote>?, Found: List<Result>

            _state.update { heroes2 }
        }
    }


    // fundamentals method
    private var heroesPresent = listOf<MarvelHeroesDTO>()
    private var heroToShow: MarvelHeroesDTO? = null
    private var seriesToShow = listOf<MarvelSeriesDTO>()

    fun getHeroes() : List<MarvelHeroesDTO> {
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
                        val getHeroesArray = gson.fromJson(response, MarvelHeroesDTO::class.java)
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

    fun getHeroByName(heroName: String) : MarvelHeroesDTO? {
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
                        val getHero = gson.fromJson(response, MarvelHeroesDTO::class.java)
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

    fun getSeries(characterId: Int) : List<MarvelSeriesDTO> {
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
                        val getSeriesArray = gson.fromJson(response, MarvelSeriesDTO::class.java)
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
}