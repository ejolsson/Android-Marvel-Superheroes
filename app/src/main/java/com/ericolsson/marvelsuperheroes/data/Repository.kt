package com.ericolsson.marvelsuperheroes.data

import com.ericolsson.marvelsuperheroes.SuperHeroRemote
import com.ericolsson.marvelsuperheroes.SuperheroDTO

interface Repository {
    suspend fun getHeroes4(): SuperheroDTO // List<SuperHeroRemote>
}