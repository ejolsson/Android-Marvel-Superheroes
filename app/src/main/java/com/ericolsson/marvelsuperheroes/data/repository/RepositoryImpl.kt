package com.ericolsson.marvelsuperheroes.data.repository

import android.util.Log
import com.ericolsson.marvelsuperheroes.SeriesRemote
import com.ericolsson.marvelsuperheroes.data.local.LocalDataSource
import com.ericolsson.marvelsuperheroes.data.local.SuperHeroDAO
import com.ericolsson.marvelsuperheroes.data.local.SuperHeroLocal
import com.ericolsson.marvelsuperheroes.data.mappers.LocalDetailToPresentationMapper
import com.ericolsson.marvelsuperheroes.data.mappers.LocalToLocalDetailMapper
import com.ericolsson.marvelsuperheroes.data.mappers.LocalToPresentationMapper
import com.ericolsson.marvelsuperheroes.data.mappers.PresentationToLocalMapper
import com.ericolsson.marvelsuperheroes.data.mappers.RemoteToLocalMapper
import com.ericolsson.marvelsuperheroes.data.mappers.RemoteToPresentationMapper
import com.ericolsson.marvelsuperheroes.data.remote.RemoteDataSource
import com.ericolsson.marvelsuperheroes.data.remote.response.ComicsRemote
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val remoteToPresentationMapper: RemoteToPresentationMapper,
    private val remoteToLocalMapper: RemoteToLocalMapper,
    private val localToPresentationMapper: LocalToPresentationMapper,
    private val presentationToLocalMapper: PresentationToLocalMapper,
    private val localDetailToPresentationMapper: LocalDetailToPresentationMapper,
    private val localToLocalDetailMapper: LocalToLocalDetailMapper,
    private val dao: SuperHeroDAO
): Repository {
    // region Non-focus
    override suspend fun getHeroes4(): List<SuperHeroLocal> {

        if (localDataSource.getHeroes3().isEmpty()) {
            Log.d("Tag", "No heroes stored locally. Going the fetch them!")
            val remoteSuperHeroes = remoteDataSource.getHeroes2()

            localDataSource.insertHeroes(remoteToLocalMapper.mapList(remoteSuperHeroes))
        }
        return localDataSource.getHeroes3() //localToPresentationMapper.mapLocalSuperHeroes(localDataSource.getHeroes3())
    }

    override suspend fun insertHero(hero: SuperHeroLocal) {
        dao.insertSuperhero(hero)
    }

    override suspend fun getHeroByName4(heroId: Long): SuperHeroLocal {
//        return localToPresentationMapper.map(localDataSource.getHeroByName3(heroId))
        return localDataSource.getHeroByName3(heroId)
    }

    override suspend fun getSeries4(id: Long): SeriesRemote {
        return remoteDataSource.getSeries2(id)
    }

    override suspend fun getComics4(id: Long): ComicsRemote {
        return remoteDataSource.getComics2(id)
    }
    // endregion
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