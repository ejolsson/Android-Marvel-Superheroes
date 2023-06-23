package com.ericolsson.marvelsuperheroes.data.local

interface LocalDataSource {
    fun getHeroes3(): List<SuperHeroLocal>
    fun getHeroByName3(id: Long): SuperHeroLocal
    fun insertHeroes(remoteSuperHeroes: List<SuperHeroLocal>)
    fun insertHero(superHeroLocal: SuperHeroLocal)
    fun deleteHero(superHeroLocal: SuperHeroLocal)
}