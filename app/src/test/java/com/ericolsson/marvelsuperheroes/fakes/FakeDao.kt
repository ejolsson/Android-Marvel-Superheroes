package com.ericolsson.marvelsuperheroes.fakes

import com.ericolsson.marvelsuperheroes.data.local.SuperHeroDAO
import com.ericolsson.marvelsuperheroes.data.local.SuperHeroLocal
import com.ericolsson.marvelsuperheroes.utils.generateSuperHeroLocalList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class FakeDao(private val shared: Boolean) : SuperHeroDAO {

    private val heroes = flow {
        emit(listOf(SuperHeroLocal(123, "name", "photo", "description",false)))
        emit(listOf(SuperHeroLocal(456, "name1", "photo1", "description1", false)))
    }

    private val sharedHeroesFlow = MutableSharedFlow<List<SuperHeroLocal>>()
    private val superHeroLocal = SuperHeroLocal(999, "name9", "photo9", "description9", false)

    suspend fun emit(superHeroLocalList: List<SuperHeroLocal>) {
        sharedHeroesFlow.emit(superHeroLocalList)
    }

    override fun getAllSuperheros(): Flow<List<SuperHeroLocal>> {
        return if (shared) {
            sharedHeroesFlow
        } else {
            heroes
        }
    }

    override fun getHeroByName(id: Long): SuperHeroLocal {
        return generateSuperHeroLocalList().find { it.id == id }!!
//        return heroes.find { it.id == id }
//        return superHeroLocal
    }

    override fun insertAll(superHeroLocalList: List<SuperHeroLocal>) {
        generateSuperHeroLocalList()
    }

    override fun insertSuperhero(superHeroLocal: SuperHeroLocal) {
        generateSuperHeroLocalList().first()
    }

    override fun insertSuperHeroLocal(superHeroLocal: SuperHeroLocal) {
        generateSuperHeroLocalList().first()
    }

    override fun deleteSuperHeroLocal(superHeroLocal: SuperHeroLocal) {
        generateSuperHeroLocalList().first()
    }

}
