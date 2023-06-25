package com.ericolsson.marvelsuperheroes.data.remote

import com.ericolsson.marvelsuperheroes.SeriesRemote
import com.ericolsson.marvelsuperheroes.data.remote.response.ComicsRemote
import com.ericolsson.marvelsuperheroes.data.remote.response.MarvelResponseObject

interface RemoteDataSource {
    suspend fun getHeroes2(): MarvelResponseObject
    suspend fun getHeroByName2(name: String): MarvelResponseObject
    suspend fun getSeries2(id: Long): SeriesRemote
    suspend fun getComics2(id: Long): ComicsRemote
}