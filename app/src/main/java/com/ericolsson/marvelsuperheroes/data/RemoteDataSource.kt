package com.ericolsson.marvelsuperheroes.data

import com.ericolsson.marvelsuperheroes.SuperHeroRemote
import com.ericolsson.marvelsuperheroes.SuperheroDTO

interface RemoteDataSource {
    suspend fun getHeroes2(): SuperheroDTO // List<SuperHeroRemote>
}

// use interface for testing and separation of prod RemoteDataSource