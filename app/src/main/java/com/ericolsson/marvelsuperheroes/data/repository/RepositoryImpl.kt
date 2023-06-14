package com.ericolsson.marvelsuperheroes.data.repository

import android.util.Log
import com.ericolsson.marvelsuperheroes.MarvelHeroesDTO
import com.ericolsson.marvelsuperheroes.SeriesRemote
import com.ericolsson.marvelsuperheroes.data.local.LocalDataSource
import com.ericolsson.marvelsuperheroes.data.mappers.LocalToPresentationMapper
import com.ericolsson.marvelsuperheroes.data.mappers.PresentationToLocalMapper
import com.ericolsson.marvelsuperheroes.data.mappers.RemoteToLocalMapper
import com.ericolsson.marvelsuperheroes.data.mappers.RemoteToPresentationMapper
import com.ericolsson.marvelsuperheroes.data.remote.RemoteDataSource
import com.ericolsson.marvelsuperheroes.data.remote.response.ComicsRemote
import com.ericolsson.marvelsuperheroes.data.remote.response.SuperHeroRemote
import com.ericolsson.marvelsuperheroes.domain.SuperHero
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val remoteToPresentationMapper: RemoteToPresentationMapper,
    private val remoteToLocalMapper: RemoteToLocalMapper,
    private val localToPresentationMapper: LocalToPresentationMapper,
    private val presentationToLocalMapper: PresentationToLocalMapper
): Repository {

    override suspend fun getHeroes4(): List<SuperHero> {

        Log.w("Tag getHeroes2", "remoteDataSource.getHeroes2(): ${remoteDataSource.getHeroes2()}")

        if (localDataSource.getHeroes3().isEmpty()) {
            Log.w("Tag", "No heroes stored locally. Going the fetch them!")
            val remoteSuperHeroes = remoteDataSource.getHeroes2()

            localDataSource.insertHeroes(remoteToLocalMapper.mapSuperHeroRemote(remoteSuperHeroes))
        }
        return localToPresentationMapper.mapLocalSuperHeroes(localDataSource.getHeroes3())
    }

    suspend fun getHeroByName4(heroName: String){//}: SuperHero {
        // All heroes should be locally stored at this point
        Log.w("Tag getHeroes2", "remoteDataSource.getHeroByName2(): ${remoteDataSource.getHeroByName2(heroName)}")

//        return remoteDataSource.getHeroByName2(heroName)

//         val result = remoteDataSource.getHeroByName2(heroName).also {
//             if (it.isSuccess){
//                 it.getOrNull()?.let { superHeroDetailRemote ->
//                     if (superHeroDetailRemote.favorite) {
//                         localDataSource.insertHero(remoteToLocalMapper.map(superHeroDetailRemote))
//                     } else {
//                         localDataSource.deleteHero(remoteToLocalMapper.map(superHeroDetailRemote))
//                     }
//                 }
//             }
//        }
//        return result.getOrNull()?.let { remoteToPresentationMapper.map(it) }
    }

    override suspend fun getSeries4(id: Long): SeriesRemote {
        return remoteDataSource.getSeries2(id) // no need add other logic, will call on the spot
    }

    override suspend fun getComics4(id: Long): ComicsRemote {
        return remoteDataSource.getComics2(id)
    }
}