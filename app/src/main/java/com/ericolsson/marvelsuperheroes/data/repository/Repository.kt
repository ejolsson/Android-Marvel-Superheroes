package com.ericolsson.marvelsuperheroes.data.repository

import com.ericolsson.marvelsuperheroes.MarvelHeroesDTO

interface Repository {
    suspend fun getHeroes4(): MarvelHeroesDTO // List<SuperHeroRemote>
}