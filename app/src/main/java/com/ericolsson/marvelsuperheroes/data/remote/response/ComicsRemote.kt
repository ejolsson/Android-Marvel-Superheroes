package com.ericolsson.marvelsuperheroes.data.remote.response

import com.squareup.moshi.Json

data class ComicsRemote(
    @Json(name = "code") val code: Long,
    @Json(name = "data") val data: ComicsData
)

data class ComicsData (
    @Json(name = "offset") val offset: Long,
    @Json(name = "limit") val limit: Long,
    @Json(name = "total") val total: Long,
    @Json(name = "count") val count: Long,
    @Json(name = "results") val results: Array<ComicsResult>
)

data class ComicsResult (
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String? = null,
    @Json(name = "thumbnail") val thumbnail: Thumbnail
)
