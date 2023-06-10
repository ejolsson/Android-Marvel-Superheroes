package com.ericolsson.marvelsuperheroes
// https://app.quicktype.io/
data class SuperheroDTO (
    val code: Long,
//    val status: String,
//    val copyright: String,
//    val attributionText: String,
//    val attributionHTML: String,
//    val etag: String,
    val data: Data
)

data class Data (
//    val offset: Long,
//    val limit: Long,
//    val total: Long,
//    val count: Long,
    val results: Array<Result>
)

data class Result ( // map to data class SuperHeroRemote
    val id: Long,
    val name: String,
    val description: String,
//    val modified: String,
    val thumbnail: Thumbnail,
//    val resourceURI: String,
//    val comics: Comics,
    val series: Comics,
//    val stories: Stories,
//    val events: Comics,
//    val urls: List<URL>
)

// MARK: - LIKELY DON'T NEED THE ITEMS BELOW -

data class Comics (
    val available: Long,
    val collectionURI: String,
    val items: Array<ComicsItem>,
    val returned: Int//Long
)

data class ComicsItem (
    val resourceURI: String,
    val name: String
)

data class Stories (
    val available: Long,
    val collectionURI: String,
    val items: Array<StoriesItem>,
    val returned: Int// Long
)

data class StoriesItem (
    val resourceURI: String,
    val name: String,
    val type: ItemType
)

enum class ItemType {
    Cover,
    Empty,
    InteriorStory,
    Profile,
    Recap
}

data class Thumbnail (
    val path: String,
    val extension: Extension
)

enum class Extension {
    Jpg
}

data class URL (
    val type: URLType,
    val url: String
)

enum class URLType {
    Comiclink,
    Detail,
    Wiki
}

//val heroDefault = Result(
//    1009368,
//    "Iron Man",
//    "Iron Man...",
//    Thumbnail(
//        "http://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55",
//        Extension.Jpg
//    ), Comics(
//        659,
//        "http://gateway.marvel.com/v1/public/characters/1009368/series",
//        listOf(
//            ComicsItem(
//                "http://gateway.marvel.com/v1/public/series/16450",
//                "A+X (2012 - 2014)"
//            ),
//            ComicsItem(
//                "http://gateway.marvel.com/v1/public/series/6079",
//                "Adam: Legend of the Blue Marvel (2008)"
//            ),
//            ComicsItem(
//                "http://gateway.marvel.com/v1/public/series/27392",
//                "Aero (2019 - 2020)"
//            ),
//            ComicsItem(
//                "http://gateway.marvel.com/v1/public/series/9790",
//                "Age of Heroes (2010)"
//            )
//        ),
//20)
//)



/*
Fm iOS

import Foundation

struct HeroModel: Codable {
//    let code: Int
    let data: DataClass
}

struct DataClass: Codable {
//    let offset, limit, total, count: Int
    let results: [Result]
}

struct Result: Codable, Identifiable {
    let id: Int
    let name, description: String
    let thumbnail: Thumbnail
    let series: Comics // don't need this. get fm 2nd api call.

}

struct Thumbnail: Codable {
    let path: String
    let thumbnailExtension: Extension

    enum CodingKeys: String, CodingKey {
        case path
        case thumbnailExtension = "extension"
    }
}

enum Extension: String, Codable {
    case jpg = "jpg"
}

// MARK: - LIKELY DON'T NEED THE ITEMS BELOW -

struct Comics: Codable {
    let available: Int
    let collectionURI: String
    let items: [ComicsItem]
    let returned: Int
}

struct ComicsItem: Codable {
    let resourceURI: String
    let name: String
}

let heroDefault = Result(
    id: 1009368,
    name: "Iron Man",
    description: "Wounded, captured and forced to build a weapon by his enemies, billionaire industrialist Tony Stark instead created an advanced suit of armor to save his life and escape captivity. Now with a new outlook on life, Tony uses his money and intelligence to make the world a safer, better place as Iron Man.",
    thumbnail: Thumbnail(
        path: "http://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55",
        thumbnailExtension: Extension.jpg
    ),
    series: Comics(
        available: 659,
        collectionURI: "http://gateway.marvel.com/v1/public/characters/1009368/series",
        items: [
            ComicsItem(
                resourceURI: "http://gateway.marvel.com/v1/public/series/16450",
                name: "A+X (2012 - 2014)"
            ),
            ComicsItem(
                resourceURI: "http://gateway.marvel.com/v1/public/series/6079",
                name: "Adam: Legend of the Blue Marvel (2008)"
            ),
            ComicsItem(
                resourceURI: "http://gateway.marvel.com/v1/public/series/27392",
                name: "Aero (2019 - 2020)"
            ),
            ComicsItem(
                resourceURI: "http://gateway.marvel.com/v1/public/series/9790",
                name: "Age of Heroes (2010)"
            )
        ],
        returned: 20)
)


//  SeriesModel.swift
    //  MarvelSwiftUI
    //
    //  Created by Eric Olsson on 3/30/23.
    //

    import Foundation


    struct SeriesModel: Codable {
        let code: Int
        let data: DataClassSeries
    }

    struct DataClassSeries: Codable {
    //    let offset, limit, total, count: Int
        let results: [SeriesResult]
    }

    struct SeriesResult: Codable, Identifiable {
        let id: Int
        let title: String
        let description: String?
        let thumbnail: Thumbnail
    }



 */