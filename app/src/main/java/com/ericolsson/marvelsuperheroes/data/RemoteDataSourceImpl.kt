package com.ericolsson.marvelsuperheroes.data

import com.ericolsson.marvelsuperheroes.SuperHeroRemote
import com.ericolsson.marvelsuperheroes.SuperheroDTO
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: MarvelApi): RemoteDataSource {

    val apikey: String = "f0c5210c2332d5d32edc3a40552edb27"
    val hash: String = "a4d396a1143f5258c6cced5dc9863a84"

    override suspend fun getHeroes2(): SuperheroDTO { // List<SuperHeroRemote>
        return api.getHeroes1(1,apikey, hash, 3, 800)
    }

}