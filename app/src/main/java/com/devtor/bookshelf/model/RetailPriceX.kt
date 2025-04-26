package com.devtor.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class RetailPriceX(
    val amount: Double,
    val currencyCode: String
)