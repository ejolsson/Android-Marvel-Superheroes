package com.ericolsson.marvelsuperheroes.data.remote

import com.ericolsson.marvelsuperheroes.MarvelHeroesDTO

interface RemoteDataSource {
    suspend fun getHeroes2(): MarvelHeroesDTO // List<SuperHeroRemote>
}

// use interface for testing and separation of prod RemoteDataSource