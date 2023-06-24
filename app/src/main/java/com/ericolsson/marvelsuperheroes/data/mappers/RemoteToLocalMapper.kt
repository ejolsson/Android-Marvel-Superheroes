package com.ericolsson.marvelsuperheroes.data.mappers

import com.ericolsson.marvelsuperheroes.data.local.SuperHeroLocal
import com.ericolsson.marvelsuperheroes.data.remote.response.SuperHeroRemote
import com.ericolsson.marvelsuperheroes.data.remote.response.MarvelResponseObject
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor() {

    fun mapList(marvelResponseObject: MarvelResponseObject): List<SuperHeroLocal> {
        return marvelResponseObject.data.results.map { mapList(it) }
    }

    private fun mapList (superHeroRemote: SuperHeroRemote): SuperHeroLocal {
        return SuperHeroLocal(
            id = superHeroRemote.id,
            name = superHeroRemote.name,
            photo = "${superHeroRemote.thumbnail.path}.${superHeroRemote.thumbnail.extension}",
        description = superHeroRemote.description,
            favorite = false
        )
    }
}