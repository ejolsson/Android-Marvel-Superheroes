package com.ericolsson.marvelsuperheroes.data.remote

import com.ericolsson.marvelsuperheroes.SeriesRemote
import com.ericolsson.marvelsuperheroes.data.remote.response.ComicsRemote
import com.ericolsson.marvelsuperheroes.data.remote.response.MarvelResponseObject
import com.ericolsson.marvelsuperheroes.data.remote.response.SuperHeroRemote

internal class FakeRemoteDataSource: RemoteDataSource {

    val apikey: String = "f0c5210c2332d5d32edc3a40552edb27"
    val hash: String = "a4d396a1143f5258c6cced5dc9863a84"

    override suspend fun getHeroes2(): MarvelResponseObject {
        TODO("Not yet implemented")
    }

    override suspend fun getHeroByName2(name: String): MarvelResponseObject {
        TODO("Not yet implemented")
    }
    override suspend fun getSeries2(id: Long): SeriesRemote {
        TODO("Not yet implemented")
    }

    override suspend fun getComics2(id: Long): ComicsRemote {
        TODO("Not yet implemented")
    }
    suspend fun getHeroesT(): List<SuperHeroRemote> {
        TODO("Not yet implemented")
    }
}