package com.devtor.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class ListPriceX(
    val amountInMicros: Int,
    val currencyCode: String
)