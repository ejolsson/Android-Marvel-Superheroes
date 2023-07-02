package com.ericolsson.marvelsuperheroes.utils

import com.ericolsson.marvelsuperheroes.data.local.SuperHeroLocal
import com.ericolsson.marvelsuperheroes.data.remote.response.Comics
import com.ericolsson.marvelsuperheroes.data.remote.response.ComicsItem
import com.ericolsson.marvelsuperheroes.data.remote.response.Extension
import com.ericolsson.marvelsuperheroes.data.remote.response.SuperHeroRemote
import com.ericolsson.marvelsuperheroes.data.remote.response.Thumbnail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlin.random.Random

fun generateSuperHeroRemoteList(): List<SuperHeroRemote> {
    return (0 until 5).map {
        SuperHeroRemote(
            id = Random.nextLong(),
            name = "Name $it",
            description = "Photo $it",
            thumbnail = Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/d/d0/5269657a74350",Extension.jpg),
            series = Comics(
                available = Random.nextLong(),
                collectionURI = "collectionURI $it",
                items = arrayOf(
                    ComicsItem(
                        resourceURI = "resourceURI $it",
                        name = "Name $it"
                    )
                ),
                returned = Random.nextInt()
            )
        )
    }
}

fun generateSuperHeroLocalList(): List<SuperHeroLocal> {
    return (0 until 5).map {
        SuperHeroLocal(
            id = Random.nextLong(),
            "Name $it",
            "Photo $it",
            "Description $it",
            false
        )
    }
}