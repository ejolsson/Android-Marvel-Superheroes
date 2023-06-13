package com.ericolsson.marvelsuperheroes.data.repository

import android.util.Log
import com.ericolsson.marvelsuperheroes.MarvelHeroesDTO
import com.ericolsson.marvelsuperheroes.data.remote.RemoteDataSource
import com.ericolsson.marvelsuperheroes.data.remote.response.SuperHeroRemote
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): Repository {

    override suspend fun getHeroes4(): SuperHeroRemote {
        Log.w("Tag getHeroes2", "remoteDataSource.getHeroes2(): ${remoteDataSource.getHeroes2()}")
        return remoteDataSource.getHeroes2()
        /* TODO: add the following
        if (localDataSource.getHeroes3().isEmpty()) {
            Log.w("Tag", "No heroes stored locally. Going the fetch them!")
            Log.d("Tag", "getHeroes4 token = $token")
            val remoteSuperHeroes = remoteDataSource.getHeroes2(token)

            localDataSource.insertHeroes(remoteToLocalMapper.mapGetHeroResponse(remoteSuperHeroes))
        }
        return localToPresentationMapper.mapLocalSuperHeroes(localDataSource.getHeroes3())
         */
    }
}