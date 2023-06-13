package com.ericolsson.marvelsuperheroes

import com.squareup.moshi.Json

data class SuperHeroRemote(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "thumbnail") val thumbnail: Thumbnail,
//    @Json(name = "Comics") val comics: Comics
)