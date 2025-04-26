package com.devtor.bookshelf.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfo(
    val allowAnonLogging: Boolean,
    val authors: List<String>?=null,
    val canonicalVolumeLink: String,
    val categories: List<String>?=null,
    val contentVersion: String,
    val description: String?=null,
    val imageLinks: ImageLinks,
    val industryIdentifiers: List<IndustryIdentifier>,
    val infoLink: String,
    val language: String,
    val maturityRating: String,
    val pageCount: Int,
    val panelizationSummary: PanelizationSummary,
    val previewLink: String,
    val printType: String,
    val publishedDate: String,
    val publisher: String?=null,
    val readingModes: ReadingModes,
    @SerialName("subtitle")
    val subtitle: String?=null,
    val title: String
)