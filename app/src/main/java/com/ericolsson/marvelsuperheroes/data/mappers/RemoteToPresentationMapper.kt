package com.ericolsson.marvelsuperheroes.data.mappers

import com.ericolsson.marvelsuperheroes.data.remote.response.Result
import com.ericolsson.marvelsuperheroes.data.remote.response.SuperHeroRemote
import com.ericolsson.marvelsuperheroes.domain.SuperHero
import javax.inject.Inject

class RemoteToPresentationMapper @Inject constructor() {

    fun map(superHeroRemote: SuperHeroRemote): List<SuperHero> {
        return superHeroRemote.data.results.map { map(it) }
    }

    private fun map(superHero: Result): SuperHero {
        return SuperHero(
            id = superHero.id,
            name = superHero.name,
            photo = superHero.thumbnail.path,
            description = superHero.description
        )
    }
}