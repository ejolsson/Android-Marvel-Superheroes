package com.ericolsson.marvelsuperheroes.data.local

interface LocalDataSource {
    fun getHeroes3(): List<SuperHeroLocal>
    fun insertHeroes(remoteSuperHeroes: List<SuperHeroLocal>)
    fun insertHero(superHeroDetailLocal: SuperHeroDetailLocal)
    fun deleteHero(superHeroDetailLocal: SuperHeroDetailLocal)
}