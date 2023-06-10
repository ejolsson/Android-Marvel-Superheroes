package com.ericolsson.marvelsuperheroes.data

import com.ericolsson.marvelsuperheroes.SuperHeroRemote

interface RemoteDataSource {
    suspend fun getHeroes2(): List<SuperHeroRemote>
}

// use interface for testing and separation of prod RemoteDataSource