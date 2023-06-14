package com.ericolsson.marvelsuperheroes.data.local

interface LocalDataSource {
    fun getHeroes3(): List<SuperHeroLocal>
//    fun getSeries3(): List<SeriesLocal> // not required locally!
    fun getHeroByName3(): SuperHeroLocal
    fun insertHeroes(remoteSuperHeroes: List<SuperHeroLocal>)
    fun insertHero(superHeroDetailLocal: SuperHeroDetailLocal)
    fun deleteHero(superHeroDetailLocal: SuperHeroDetailLocal)
}