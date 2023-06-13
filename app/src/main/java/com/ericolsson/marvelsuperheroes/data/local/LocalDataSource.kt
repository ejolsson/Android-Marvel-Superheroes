package com.ericolsson.marvelsuperheroes.data.local

interface LocalDataSource {
    fun getHeroes(): List<SuperHeroLocal>
    fun insertHeroes(remoteSuperHeroes: List<SuperHeroLocal>)
    fun insertHero(superHeroDetailLocal: SuperHeroDetailLocal)
    fun deleteHero(superHeroDetailLocal: SuperHeroDetailLocal)
}