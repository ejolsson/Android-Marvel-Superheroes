package com.ericolsson.marvelsuperheroes.data.mappers

import com.ericolsson.marvelsuperheroes.data.local.SuperHeroDetailLocal
import com.ericolsson.marvelsuperheroes.domain.SuperHero
import com.ericolsson.marvelsuperheroes.domain.SuperHeroDetail
import javax.inject.Inject
// SuperHero → SuperHeroDetailLocal
class PresentationToLocalMapper @Inject constructor() {

    fun map(superHeroList: List<SuperHeroDetail>): List<SuperHeroDetailLocal> {
        return superHeroList.map { map(it) }
    }

    fun map(superHeroDetail: SuperHeroDetail): SuperHeroDetailLocal {
        return SuperHeroDetailLocal(
            id = superHeroDetail.id,
            name = superHeroDetail.name,
            description = superHeroDetail.description,
            photo = superHeroDetail.photo,
            favorite = superHeroDetail.favorite
        )
    }
    fun map2(superHero: SuperHero): SuperHeroDetailLocal {
        return SuperHeroDetailLocal(
            id = superHero.id,
            name = superHero.name,
            description = superHero.description,
            photo = superHero.photo,
            favorite = superHero.favorite
        )
    }
}