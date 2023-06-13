package com.ericolsson.marvelsuperheroes.data.repository

import com.ericolsson.marvelsuperheroes.MarvelHeroesDTO
import com.ericolsson.marvelsuperheroes.data.remote.response.SuperHeroRemote

interface Repository {
    suspend fun getHeroes4(): SuperHeroRemote // List<SuperHeroRemote>
}