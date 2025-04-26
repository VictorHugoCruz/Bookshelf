package com.devtor.bookshelf.model

data class BookInfo(
    val title: String,
    val author: List<String>?,
    val date: String,
    val description: String?,
    val category: List<String>?,
    val image: String
)
