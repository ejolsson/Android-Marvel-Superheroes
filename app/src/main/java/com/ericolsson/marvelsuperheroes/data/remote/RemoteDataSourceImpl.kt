package com.ericolsson.marvelsuperheroes.data.remote

import com.ericolsson.marvelsuperheroes.SeriesRemote
import com.ericolsson.marvelsuperheroes.data.MarvelApi
import com.ericolsson.marvelsuperheroes.data.remote.response.ComicsRemote
import com.ericolsson.marvelsuperheroes.data.remote.response.SuperHeroRemote
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: MarvelApi): RemoteDataSource {

    val apikey: String = "f0c5210c2332d5d32edc3a40552edb27"
    val hash: String = "a4d396a1143f5258c6cced5dc9863a84"

    override suspend fun getHeroes2(): SuperHeroRemote { // List<SuperHeroRemote>
        return api.getHeroes1(1,apikey, hash, 3, 800)
    }

    override suspend fun getHeroByName2(name: String): SuperHeroRemote {
        return api.getHeroByName1(1,apikey,hash, name)
    }
    override suspend fun getSeries2(id: Long): SeriesRemote {
        return api.getSeries1(id,1,apikey,hash,3) // "${id}/series"
    }

    override suspend fun getComics2(id: Long): ComicsRemote {
        return api.getComics1(id,1,apikey,hash,2)
    }
    suspend fun getHeroes3(): SuperHeroRemote { // todo: for testing
        return api.getHeroes1(1, apikey, hash, 3, 800)
        // return result.filter { it.name.startsWith("B") }
    }

}