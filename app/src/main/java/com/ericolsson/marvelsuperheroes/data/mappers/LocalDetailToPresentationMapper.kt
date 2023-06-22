package com.ericolsson.marvelsuperheroes.data.mappers

import com.ericolsson.marvelsuperheroes.data.local.SuperHeroDetailLocal
import com.ericolsson.marvelsuperheroes.domain.SuperHeroDetail
import javax.inject.Inject

class LocalDetailToPresentationMapper @Inject constructor() {

//    fun mapList(superHeroList: List<SuperHeroLocal>): List<SuperHero> {
//        return superHeroList.map { map(it) }
//    }

    fun map(superHeroDetailLocal: SuperHeroDetailLocal): SuperHeroDetail {
        return SuperHeroDetail(
            id = superHeroDetailLocal.id,
            name = superHeroDetailLocal.name,
            photo = superHeroDetailLocal.photo,
            description = superHeroDetailLocal.description,
            favorite = superHeroDetailLocal.favorite
        )
    }
}