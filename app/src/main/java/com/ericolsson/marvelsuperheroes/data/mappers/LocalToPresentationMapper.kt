package com.ericolsson.marvelsuperheroes.data.mappers

import com.ericolsson.marvelsuperheroes.data.local.SuperHeroLocal
import com.ericolsson.marvelsuperheroes.domain.SuperHero
import javax.inject.Inject
// SuperHeroLocal(s) → SuperHero(s)
class LocalToPresentationMapper @Inject constructor() {

    fun mapLocalSuperHeroes(superHeroLocalList: List<SuperHeroLocal>): List<SuperHero> {
        return superHeroLocalList.map { map(it) }
    }

    fun map(superHeroLocal: SuperHeroLocal): SuperHero {
        return SuperHero(
            id = superHeroLocal.id,
            name = superHeroLocal.name,
            photo = superHeroLocal.photo,
            description = superHeroLocal.description,
            favorite = superHeroLocal.favorite
        )
    }
}