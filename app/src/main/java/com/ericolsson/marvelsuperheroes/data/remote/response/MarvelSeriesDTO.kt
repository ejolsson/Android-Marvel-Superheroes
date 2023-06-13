package com.ericolsson.marvelsuperheroes

data class MarvelSeriesDTO (
    val code: Long,
//    val status: String,
//    val copyright: String,
//    val attributionText: String,
//    val attributionHTML: String,
//    val etag: String,
    val data: SeriesData
)

data class SeriesData (
    val offset: Long,
    val limit: Long,
    val total: Long,
    val count: Long,
    val results: Array<SeriesResult>
)

data class SeriesResult (
    val id: Long,
    val title: String,
    val description: String? = null,
//    val resourceURI: String,
//    val urls: List<URL>,
//    val startYear: Long,
//    val endYear: Long,
//    val rating: String,
//    val type: String,
//    val modified: String,
    val thumbnail: Thumbnail,
//    val creators: Creators,
//    val characters: Characters,
//    val stories: Stories,
//    val comics: Characters,
//    val events: Characters,
//    val next: Any? = null,
//    val previous: Previous? = null
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

data class Thumbnail (
    val path: String,
    val extension: String
)

data class URL (
    val type: String,
    val url: String
)
 */



