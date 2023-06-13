package com.ericolsson.marvelsuperheroes.data.mappers

import com.ericolsson.marvelsuperheroes.data.local.SuperHeroDetailLocal
import com.ericolsson.marvelsuperheroes.domain.SuperHeroDetail
import javax.inject.Inject

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
}