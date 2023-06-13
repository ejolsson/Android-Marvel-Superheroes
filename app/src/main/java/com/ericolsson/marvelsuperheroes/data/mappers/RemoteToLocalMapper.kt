package com.ericolsson.marvelsuperheroes.data.mappers

import com.ericolsson.marvelsuperheroes.data.local.SuperHeroLocal
import com.ericolsson.marvelsuperheroes.data.remote.response.Result
import com.ericolsson.marvelsuperheroes.data.remote.response.SuperHeroRemote
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor() {

    fun mapSuperHeroRemote(getHeroesResponse: SuperHeroRemote): List<SuperHeroLocal> {
        return getHeroesResponse.data.results.map { map(it) }
    }

    private fun map (getHeroesResponse: Result): SuperHeroLocal {
        return SuperHeroLocal(
            id = getHeroesResponse.id,
            name = getHeroesResponse.name,
            photo = getHeroesResponse.thumbnail.path,
        description = getHeroesResponse.description,
            favorite = false
        )
    }
}