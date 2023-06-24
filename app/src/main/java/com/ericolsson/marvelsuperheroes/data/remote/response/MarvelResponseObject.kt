package com.ericolsson.marvelsuperheroes.data.remote.response

import com.squareup.moshi.Json

data class MarvelResponseObject(
    @Json(name = "code") val code: Long,
    @Json(name = "data") val data: Data,
)

data class Data (
    @Json(name = "results") val results: Array<SuperHeroRemote>
)

data class SuperHeroRemote (
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "thumbnail") val thumbnail: Thumbnail,
    @Json(name = "series") val series: Comics,
)

data class Comics (
    @Json(name = "available") val available: Long,
    @Json(name = "collectionURI") val collectionURI: String,
    @Json(name = "items") val items: Array<ComicsItem>,
    @Json(name = "returned") val returned: Int //Long
)

data class ComicsItem (
    @Json(name = "resourceURI") val resourceURI: String,
    @Json(name = "name") val name: String
)

data class StoriesItem (
    @Json(name = "resourceURI") val resourceURI: String,
    @Json(name = "name") val name: String,
    @Json(name = "type") val type: ItemType
)

enum class ItemType {
    @Json(name = "Cover") Cover,
    @Json(name = "Empty") Empty,
    @Json(name = "InteriorStory") InteriorStory,
    @Json(name = "Profile") Profile,
    @Json(name = "Recap") Recap
}

data class Thumbnail (
    @Json(name = "path") val path: String,
    @Json(name = "extension") val extension: Extension
)

enum class Extension {
    @Json(name = "jpg") jpg
}