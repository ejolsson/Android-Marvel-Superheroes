package com.ericolsson.marvelsuperheroes.data.mappers

import com.ericolsson.marvelsuperheroes.data.local.SuperHeroLocal
import com.ericolsson.marvelsuperheroes.data.remote.response.Result
import com.ericolsson.marvelsuperheroes.data.remote.response.SuperHeroRemote
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor() {

    fun mapSuperHeroRemote(superHeroRemote: SuperHeroRemote): List<SuperHeroLocal> {
        return superHeroRemote.data.results.map { map(it) }
    }

    private fun map (marvelHeroesResponse: Result): SuperHeroLocal {
        return SuperHeroLocal(
            id = marvelHeroesResponse.id,
            name = marvelHeroesResponse.name,
            photo = marvelHeroesResponse.thumbnail.path,
        description = marvelHeroesResponse.description,
            favorite = false
        )
    }
}