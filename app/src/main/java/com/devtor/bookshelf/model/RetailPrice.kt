package com.devtor.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class RetailPrice(
    val amountInMicros: Int,
    val currencyCode: String
)