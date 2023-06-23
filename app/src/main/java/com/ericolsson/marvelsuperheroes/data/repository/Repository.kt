package com.ericolsson.marvelsuperheroes.data.repository

import com.ericolsson.marvelsuperheroes.SeriesRemote
import com.ericolsson.marvelsuperheroes.data.local.SuperHeroLocal
import com.ericolsson.marvelsuperheroes.data.remote.response.ComicsRemote

interface Repository {
    suspend fun getHeroes4(): List<SuperHeroLocal>
    suspend fun insertHero(hero: SuperHeroLocal)
    suspend fun getHeroByName4(id: Long): SuperHeroLocal
    suspend fun getSeries4(id: Long): SeriesRemote
    suspend fun getComics4(id: Long): ComicsRemote
    suspend fun insertFav(superHeroLocal: SuperHeroLocal) // type?
    suspend fun deleteFav(superHeroLocal: SuperHeroLocal)
//    suspend fun countFavs(superHeroLocalList: List<SuperHeroLocal>): Int
}