package com.ericolsson.marvelsuperheroes.di

import com.ericolsson.marvelsuperheroes.data.MarvelApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val ts: Int = 1
    val apikey: String = "f0c5210c2332d5d32edc3a40552edb27"
    val hash: String = "a4d396a1143f5258c6cced5dc9863a84"
    val limit: Int = 3
    val offset: Int = 800

    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun providesOkhttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun providesApi(retrofit: Retrofit): MarvelApi {
        return retrofit.create(MarvelApi::class.java)
    }

    /*
       private val moshi = Moshi.Builder()
           .addLast(KotlinJsonAdapterFactory())
           .build()

       private val okHttpClient =
           OkHttpClient.Builder()
               .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                   level = HttpLoggingInterceptor.Level.BODY
               }).build()

       private val retrofit = Retrofit.Builder()
           .baseUrl("https://gateway.marvel.com")
           .client(okHttpClient)
           .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
           .build()

       private val marvelApi: MarvelApi = retrofit.create(MarvelApi::class.java)

       suspend fun getHeroes2(): List<SuperHeroRemote> {
           return marvelApi.getHeroes1(1,apikey,hash,3,800)
       }
   */
}