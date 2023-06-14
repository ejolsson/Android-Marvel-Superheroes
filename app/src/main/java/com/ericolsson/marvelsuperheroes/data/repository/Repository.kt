package com.ericolsson.marvelsuperheroes.data.repository

import com.ericolsson.marvelsuperheroes.MarvelHeroesDTO
import com.ericolsson.marvelsuperheroes.SeriesRemote
import com.ericolsson.marvelsuperheroes.data.remote.response.ComicsRemote
import com.ericolsson.marvelsuperheroes.data.remote.response.SuperHeroRemote
import com.ericolsson.marvelsuperheroes.domain.SuperHero

interface Repository {
    suspend fun getHeroes4(): List<SuperHero>
//    suspend fun getHeroByName4(): SuperHero
    suspend fun getSeries4(id: Long): SeriesRemote
    suspend fun getComics4(id: Long): ComicsRemote
}