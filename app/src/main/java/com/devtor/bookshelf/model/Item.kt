package com.devtor.bookshelf.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val accessInfo: AccessInfo,
    @SerialName("etag")
    val eTag: String,
    val id: String,
    val kind: String,
    val saleInfo: SaleInfo,
    val searchInfo: SearchInfo,
    val selfLink: String,
    val volumeInfo: VolumeInfo
){
    fun getInfoBook(): BookInfo{
        return BookInfo(
            title = volumeInfo.title,
            author = volumeInfo.authors,
            date = volumeInfo.publishedDate,
            description = volumeInfo.description,
            category = volumeInfo.categories,
            image = volumeInfo.imageLinks.thumbnail,
        )
    }
}