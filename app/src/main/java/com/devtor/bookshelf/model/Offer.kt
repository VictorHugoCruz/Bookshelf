package com.devtor.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    val finskyOfferType: Int,
    val listPrice: ListPriceX,
    val retailPrice: RetailPrice
)