package com.ericolsson.marvelsuperheroes.data.repository

import com.ericolsson.marvelsuperheroes.MarvelHeroesDTO
import com.ericolsson.marvelsuperheroes.data.remote.response.SuperHeroRemote
import com.ericolsson.marvelsuperheroes.domain.SuperHero

interface Repository {
    suspend fun getHeroes4(): List<SuperHero>
}