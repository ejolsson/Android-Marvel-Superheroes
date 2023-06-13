package com.ericolsson.marvelsuperheroes.data.remote

import com.ericolsson.marvelsuperheroes.MarvelHeroesDTO
import com.ericolsson.marvelsuperheroes.data.remote.response.SuperHeroRemote

interface RemoteDataSource {
    suspend fun getHeroes2(): SuperHeroRemote // List<SuperHeroRemote>
}

// use interface for testing and separation of prod RemoteDataSource