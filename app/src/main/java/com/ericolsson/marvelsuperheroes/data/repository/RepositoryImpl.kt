package com.ericolsson.marvelsuperheroes.data.repository

import com.ericolsson.marvelsuperheroes.SeriesRemote
import com.ericolsson.marvelsuperheroes.data.local.LocalDataSource
import com.ericolsson.marvelsuperheroes.data.local.SuperHeroDAO
import com.ericolsson.marvelsuperheroes.data.local.SuperHeroLocal
import com.ericolsson.marvelsuperheroes.data.mappers.RemoteToLocalMapper
import com.ericolsson.marvelsuperheroes.data.remote.RemoteDataSource
import com.ericolsson.marvelsuperheroes.data.remote.response.ComicsRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import util.Log
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val remoteToLocalMapper: RemoteToLocalMapper,
    private val dao: SuperHeroDAO
): Repository {

    override suspend fun getHeroes4(): Flow<List<SuperHeroLocal>> {

        val localHeroes = localDataSource.getHeroes3().first()

        if (localHeroes.isEmpty()) {
            Log.d("Tag", "No heroes stored locally. Going the fetch them!")
            val remoteSuperHeroes = remoteDataSource.getHeroes2()

            localDataSource.insertHeroes(remoteToLocalMapper.mapList(remoteSuperHeroes))
        }
        return localDataSource.getHeroes3()
    }

    override suspend fun insertHero(hero: SuperHeroLocal) {
        dao.insertSuperhero(hero)
    }

    override suspend fun getHeroByName4(heroId: Long): SuperHeroLocal {
        return localDataSource.getHeroByName3(heroId)
    }

    override suspend fun getSeries4(id: Long): SeriesRemote {
        return remoteDataSource.getSeries2(id)
    }

    override suspend fun getComics4(id: Long): ComicsRemote {
        return remoteDataSource.getComics2(id)
    }

    // Insert "favorite" SuperHero to SuperHeroDetailLocal
    override suspend fun insertFav(superHeroLocal: SuperHeroLocal) {
        localDataSource.insertHero(superHeroLocal)
    }

    override suspend fun deleteFav(superHeroLocal: SuperHeroLocal) {
        localDataSource.deleteHero(superHeroLocal)
    }

//    override suspend fun countFavs(superHeroLocalList: List<SuperHeroLocal>): Int {
//        return localDataSource.countFavs(superHeroLocalList)
//    }
}