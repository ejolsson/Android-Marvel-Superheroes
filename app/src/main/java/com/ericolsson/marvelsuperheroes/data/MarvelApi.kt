package com.ericolsson.marvelsuperheroes.data

import com.ericolsson.marvelsuperheroes.SeriesRemote
import com.ericolsson.marvelsuperheroes.data.remote.response.ComicsRemote
import com.ericolsson.marvelsuperheroes.data.remote.response.MarvelResponseObject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getHeroes1(
        @Query("ts") ts: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): MarvelResponseObject

    @GET("/v1/public/characters")
    suspend fun getHeroByName1(
        @Query("ts") ts: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("name") name: String,
    ): MarvelResponseObject

    @GET("/v1/public/characters/{characterId}/series")
    suspend fun getSeries1(
        @Path("characterId") characterId: Long,
        @Query("ts") ts: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int
    ): SeriesRemote

    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun getComics1(
        @Path("characterId") characterId: Long,
        @Query("ts") ts: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int
    ): ComicsRemote
}