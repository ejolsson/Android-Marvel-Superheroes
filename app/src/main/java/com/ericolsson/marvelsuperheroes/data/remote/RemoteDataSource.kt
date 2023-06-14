package com.ericolsson.marvelsuperheroes.data.remote

import com.ericolsson.marvelsuperheroes.MarvelHeroesDTO
import com.ericolsson.marvelsuperheroes.SeriesRemote
import com.ericolsson.marvelsuperheroes.data.remote.response.SuperHeroRemote

interface RemoteDataSource {
    suspend fun getHeroes2(): SuperHeroRemote
    suspend fun getHeroByName2(name: String): SuperHeroRemote
    suspend fun getSeries2(id: Long): SeriesRemote
}

// use interface for testing and separation of prod RemoteDataSource