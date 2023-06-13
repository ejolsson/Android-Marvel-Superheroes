package com.ericolsson.marvelsuperheroes.data

import com.ericolsson.marvelsuperheroes.MarvelHeroesDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getHeroes1(
        @Query("ts") ts: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): MarvelHeroesDTO // List<SuperHeroRemote>
}

// https://square.github.io/retrofit/