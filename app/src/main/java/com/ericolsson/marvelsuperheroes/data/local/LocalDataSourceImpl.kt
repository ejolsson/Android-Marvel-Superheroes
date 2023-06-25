package com.ericolsson.marvelsuperheroes.data.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: SuperHeroDAO
) : LocalDataSource {

    override fun getHeroes3(): Flow<List<SuperHeroLocal>> {
        return dao.getAllSuperheros()
    }

    override fun getHeroByName3(id: Long): SuperHeroLocal {
        return dao.getHeroByName(id)
    }
    override fun insertHeroes(superHeroLocalList: List<SuperHeroLocal>) {
        dao.insertAll(superHeroLocalList)
    }

    override fun insertHero(superHeroLocal: SuperHeroLocal) {
        dao.insertSuperHeroLocal(superHeroLocal)
    }

    override fun deleteHero(superHeroLocal: SuperHeroLocal) {
        dao.deleteSuperHeroLocal(superHeroLocal)
    }

//    override fun countFavs(superHeroLocalList: List<SuperHeroLocal>): Int {
//        return dao.countAllFavs(superHeroLocalList)
//    }
}
