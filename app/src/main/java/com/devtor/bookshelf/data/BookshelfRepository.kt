package com.devtor.bookshelf.data

import com.devtor.bookshelf.model.BookInfo
import com.devtor.bookshelf.network.BookshelfApiService

interface BookshelfRepository {
    suspend fun getBookshelfData(): List<BookInfo>
}

class NetworkBookshelfRepository(
    val retrofitService: BookshelfApiService
): BookshelfRepository{
    override suspend fun getBookshelfData(): List<BookInfo> {
        return retrofitService.getResponse().items.map{
            it.getInfoBook()
        }
    }
}