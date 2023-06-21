package com.ericolsson.marvelsuperheroes.data.mappers

import com.ericolsson.marvelsuperheroes.data.local.SuperHeroLocal
import com.ericolsson.marvelsuperheroes.domain.SuperHero
import javax.inject.Inject

class LocalToPresentationMapper @Inject constructor() {

    fun mapLocalSuperHeroes(superHeroList: List<SuperHeroLocal>): List<SuperHero> {
        return superHeroList.map { map(it) }
    }

    fun map(superHero: SuperHeroLocal): SuperHero {
        return SuperHero(
            id = superHero.id,
            name = superHero.name,
            photo = superHero.photo,
            description = superHero.description,
            favorite = superHero.favorite
        )
    }
}