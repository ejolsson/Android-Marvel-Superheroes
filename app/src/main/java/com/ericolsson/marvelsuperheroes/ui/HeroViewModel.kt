package com.ericolsson.marvelsuperheroes.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ericolsson.marvelsuperheroes.R
import com.ericolsson.marvelsuperheroes.data.local.SuperHeroLocal
import com.ericolsson.marvelsuperheroes.data.mappers.ComicsRemoteToPresentationMapper
import com.ericolsson.marvelsuperheroes.data.mappers.SeriesRemoteToPresentationMapper
import com.ericolsson.marvelsuperheroes.data.repository.Repository
import com.ericolsson.marvelsuperheroes.domain.ComicsPresent
import com.ericolsson.marvelsuperheroes.domain.SeriesPresent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    private val _heroListState = MutableStateFlow<List<SuperHeroLocal>>(emptyList())
    val heroListState: StateFlow<List<SuperHeroLocal>> get() = _heroListState

    // HeroDetail
    private var _heroState = MutableStateFlow<SuperHeroLocal>(heroSample)
    val heroState: StateFlow<SuperHeroLocal> get() = _heroState

    private val _seriesState = MutableStateFlow<List<SeriesPresent>>(emptyList())
    val seriesState: StateFlow<List<SeriesPresent>> get() = _seriesState

    private val _comicsState = MutableStateFlow<List<ComicsPresent>>(emptyList())
    val comicsState: StateFlow<List<ComicsPresent>> get() = _comicsState

    private var _favCountState = MutableStateFlow(0)
    val favCountState: StateFlow<Int> get() = _favCountState

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
            Log.d("Tag Fav", "_heroState: ${_heroState.value}")
        }
    }
//    fun insertSuperhero(hero: SuperHero) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.insertHero(hero)
//        }
//    }
    fun getSeries5(heroId: Long) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getSeries4(heroId)
            }
            _seriesState.update { seriesRemoteToPresentationMapper.mapList(result) } // point where api data gets assigned
            Log.d("Tag", "_seriesState: ${_seriesState.value}")
        }
    }

    fun getComics5(heroId: Long) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getComics4(heroId)
            }
            _comicsState.update { comicsRemoteToPresentationMapper.mapList(result) }
            Log.d("Tag", "_comicsState: ${_comicsState.value}")
        }
    }

    fun toggleFavorite(hero: SuperHeroLocal) {
        viewModelScope.launch {
            Log.d("Tag Fav", "hero.fav before: $hero")
            val heroTemp = heroState.value
            val hero = withContext(Dispatchers.Default) {
                val hero = heroTemp.copy(favorite = !heroTemp.favorite)
                repository.insertFav(hero)
                hero
            }
            _heroState.value = hero
            Log.w("Tag Fav", "hero.fav after: $hero")
        }
    }

//    fun countFavorites(heroes: List<SuperHeroLocal>) {
//        viewModelScope.launch {
//            val heroes = heroListState.value
//            val result = withContext(Dispatchers.Default) {
//                repository.countFavs(heroes)
//            }
//            _favCountState.update { result }
//            Log.w("Tag Fav", "countFavorites = $result")
////            _favCountState.value = result
//        }
//    }
}