package com.devtor.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class Epub(
    val acsTokenLink: String?=null,
    val downloadLink: String?=null,
    val isAvailable: Boolean
)