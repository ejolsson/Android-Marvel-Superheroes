package com.ericolsson.marvelsuperheroes.data

import com.ericolsson.marvelsuperheroes.SuperHeroRemote
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): Repository {

    override suspend fun getHeroes4(): List<SuperHeroRemote> {
        TODO("Not yet implemented")
    }
}