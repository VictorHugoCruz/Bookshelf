package com.devtor.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class ReadingModes(
    val image: Boolean,
    val text: Boolean
)