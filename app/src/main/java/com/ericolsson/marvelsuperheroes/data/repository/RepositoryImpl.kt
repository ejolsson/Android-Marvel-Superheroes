package com.ericolsson.marvelsuperheroes.data.repository

import android.util.Log
import com.ericolsson.marvelsuperheroes.MarvelHeroesDTO
import com.ericolsson.marvelsuperheroes.data.local.LocalDataSource
import com.ericolsson.marvelsuperheroes.data.mappers.LocalToPresentationMapper
import com.ericolsson.marvelsuperheroes.data.mappers.PresentationToLocalMapper
import com.ericolsson.marvelsuperheroes.data.mappers.RemoteToLocalMapper
import com.ericolsson.marvelsuperheroes.data.mappers.RemoteToPresentationMapper
import com.ericolsson.marvelsuperheroes.data.remote.RemoteDataSource
import com.ericolsson.marvelsuperheroes.data.remote.response.SuperHeroRemote
import com.ericolsson.marvelsuperheroes.domain.SuperHero
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
}