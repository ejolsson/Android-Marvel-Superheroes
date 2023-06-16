package com.ericolsson.marvelsuperheroes

import com.ericolsson.marvelsuperheroes.data.remote.response.Thumbnail
import com.squareup.moshi.Json

data class SeriesRemote (
    @Json(name = "code") val code: Long,
    @Json(name = "data") val data: SeriesData
)

data class SeriesData (
    @Json(name = "offset") val offset: Long,
    @Json(name = "limit") val limit: Long,
    @Json(name = "total") val total: Long,
    @Json(name = "count") val count: Long,
    @Json(name = "results") val results: Array<SeriesResult>
)

data class SeriesResult (
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,//? = null,
    @Json(name = "thumbnail") val thumbnail: Thumbnail
)

/*
data class Characters (
    val available: Long,
    val collectionURI: String,
    val items: List<Previous>,
    val returned: Long
)

data class Previous (
    val resourceURI: String,
    val name: String
)

data class Creators (
    val available: Long,
    val collectionURI: String,
    val items: List<CreatorsItem>,
    val returned: Long
)

data class CreatorsItem (
    val resourceURI: String,
    val name: String,
    val role: String
)

data class Stories (
    val available: Long,
    val collectionURI: String,
    val items: List<StoriesItem>,
    val returned: Long
)

data class StoriesItem (
    val resourceURI: String,
    val name: String,
    val type: Type
)

enum class Type {
    Cover,
    InteriorStory
}


data class URL (
    val type: String,
    val url: String
)
 */



