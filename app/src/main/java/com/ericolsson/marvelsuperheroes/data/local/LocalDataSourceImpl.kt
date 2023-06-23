package com.ericolsson.marvelsuperheroes.data.local

import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: SuperHeroDAO
) : LocalDataSource {
    // region Non-focus
    override fun getHeroes3(): List<SuperHeroLocal> {
        return dao.getAllSuperheros()
    }

//    override fun getSeries3(): List<SeriesLocal> {
//        TODO("Not yet implemented")
//    }
    // endregion
    override fun getHeroByName3(id: Long): SuperHeroLocal {
        return dao.getHeroByName(id)
    }
    override fun insertHeroes(superHeroLocalList: List<SuperHeroLocal>) {
        dao.insertAll(superHeroLocalList)
    }
    // endregion
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
