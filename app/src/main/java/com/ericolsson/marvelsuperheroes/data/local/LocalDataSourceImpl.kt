package com.ericolsson.marvelsuperheroes.data.local

import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: SuperHeroDAO
) : LocalDataSource {
    override fun getHeroes3(): List<SuperHeroLocal> {
        return dao.getAllSuperheros()
    }

//    override fun getSeries3(): List<SeriesLocal> {
//        TODO("Not yet implemented")
//    }
    override fun getHeroByName3(id: Long): SuperHeroLocal {
        return dao.getHeroByName(id)
    }
    override fun insertHeroes(remoteSuperHeros: List<SuperHeroLocal>) {
        dao.insertAll(remoteSuperHeros)
    }

    override fun insertHero(superHeroDetailLocal: SuperHeroDetailLocal) {
        dao.insertSuperheroDetail(superHeroDetailLocal)
    }

    override fun deleteHero(superHeroDetailLocal: SuperHeroDetailLocal) {
        dao.deleteSuperHeroDetail(superHeroDetailLocal)
    }
}