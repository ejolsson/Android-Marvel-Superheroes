package com.ericolsson.marvelsuperheroes.data

import com.ericolsson.marvelsuperheroes.SuperHeroRemote

interface Repository {
    suspend fun getHeroes4(): List<SuperHeroRemote>
}