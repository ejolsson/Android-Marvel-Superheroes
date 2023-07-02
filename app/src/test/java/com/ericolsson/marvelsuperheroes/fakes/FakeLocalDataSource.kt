package com.ericolsson.marvelsuperheroes.fakes

import com.ericolsson.marvelsuperheroes.data.local.LocalDataSource
import com.ericolsson.marvelsuperheroes.data.local.SuperHeroLocal
import com.ericolsson.marvelsuperheroes.utils.generateSuperHeroLocalList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeLocalDataSource: LocalDataSource {

    private val sharedHeroesFlow = MutableSharedFlow<List<SuperHeroLocal>>()

    override fun getHeroes3(): Flow<List<SuperHeroLocal>> {
//        TODO("Not yet implemented")
//            return generateSuperHeroLocalList()
        return sharedHeroesFlow
    }

    override fun getHeroByName3(id: Long): SuperHeroLocal {
        TODO("Not yet implemented")
    }

    override fun insertHeroes(superHeroLocalList: List<SuperHeroLocal>) {
        TODO("Not yet implemented")
    }

    override fun insertHero(superHeroLocal: SuperHeroLocal) {
        TODO("Not yet implemented")
    }

    override fun deleteHero(superHeroLocal: SuperHeroLocal) {
        TODO("Not yet implemented")
    }
}