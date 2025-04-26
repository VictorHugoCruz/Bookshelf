package com.devtor.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class Pdf(
    val acsTokenLink: String?=null,
    val isAvailable: Boolean
)