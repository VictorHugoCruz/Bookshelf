package com.devtor.bookshelf.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class AccessInfo(
    val accessViewStatus: String,
    val country: String,
    val embeddable: Boolean,
    val epub: Epub,
    val pdf: Pdf,
    val publicDomain: Boolean,
    val quoteSharingAllowed: Boolean,
    val textToSpeechPermission: String,
    @SerialName("viewability")
    val viewAbility: String,
    val webReaderLink: String
)