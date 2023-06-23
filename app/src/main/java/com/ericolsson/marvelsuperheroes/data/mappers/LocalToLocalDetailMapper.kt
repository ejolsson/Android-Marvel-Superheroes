package com.ericolsson.marvelsuperheroes.data.mappers

import com.ericolsson.marvelsuperheroes.data.local.SuperHeroDetailLocal
import com.ericolsson.marvelsuperheroes.data.local.SuperHeroLocal
import javax.inject.Inject

// SuperHeroLocal(s) → SuperHeroDetailLocal(s)
class LocalToLocalDetailMapper @Inject constructor() {

    fun map(superHeroLocalList: List<SuperHeroLocal>): List<SuperHeroDetailLocal> {
        return superHeroLocalList.map { map(it) }
    }

    fun map(superHeroLocal: SuperHeroLocal): SuperHeroDetailLocal {
        return SuperHeroDetailLocal(
            id = superHeroLocal.id,
            name = superHeroLocal.name,
            photo = superHeroLocal.photo,
            description = superHeroLocal.description,
            favorite = superHeroLocal.favorite
        )
    }
}