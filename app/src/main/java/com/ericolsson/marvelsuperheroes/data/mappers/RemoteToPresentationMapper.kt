package com.ericolsson.marvelsuperheroes.data.mappers

import com.ericolsson.marvelsuperheroes.SeriesRemote
import com.ericolsson.marvelsuperheroes.SeriesResult
import com.ericolsson.marvelsuperheroes.data.remote.response.ComicsRemote
import com.ericolsson.marvelsuperheroes.data.remote.response.ComicsResult
import com.ericolsson.marvelsuperheroes.data.remote.response.Result
import com.ericolsson.marvelsuperheroes.data.remote.response.SuperHeroRemote
import com.ericolsson.marvelsuperheroes.domain.ComicsPresent
import com.ericolsson.marvelsuperheroes.domain.SeriesPresent
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
            photo = "${superHero.thumbnail.path}.${superHero.thumbnail.extension}",
            description = superHero.description,
            favorite = false // todo: not sure to do this... leave off?
        )
    }
}

class SeriesRemoteToPresentationMapper @Inject constructor() {

    fun map(seriesRemote: SeriesRemote): List<SeriesPresent> {
        return seriesRemote.data.results.map { map(it) }
    }

    private fun map(series: SeriesResult): SeriesPresent {
        return SeriesPresent(
            id = series.id,
            title = series.title,
            photo = "${series.thumbnail.path}.${series.thumbnail.extension}",
            description = series.description
        )
    }
}

class ComicsRemoteToPresentationMapper @Inject constructor() {

    fun map(comicsRemote: ComicsRemote): List<ComicsPresent> {
        return comicsRemote.data.results.map { map(it) }
    }

    private fun map(comics: ComicsResult): ComicsPresent {
        return ComicsPresent(
            id = comics.id,
            title = comics.title,
            photo = "${comics.thumbnail.path}.${comics.thumbnail.extension}",
            description = comics.description
        )
    }
}