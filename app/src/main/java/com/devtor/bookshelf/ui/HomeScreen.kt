package com.devtor.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.devtor.bookshelf.model.BookInfo

@Composable
fun HomeScreen(modifier: Modifier = Modifier, uiState: BookshelfUiState) {
    when (uiState) {
        is BookshelfUiState.Loading -> Text(text = "loading")
        is BookshelfUiState.Success -> BooksGridScreen(
            books = uiState.books,
            modifier = modifier.fillMaxSize(),

        )
        is BookshelfUiState.Error -> ErrorScreen(modifier = modifier)
    }
}

@Composable
fun ErrorScreen(modifier: Modifier=Modifier) {
    Text(text = "Error")
}

@Composable
fun BooksGridScreen(books: List<BookInfo>, modifier: Modifier=Modifier) {
    Text(modifier=modifier.fillMaxSize(), text = "Success ${books.size}", textAlign = TextAlign.Center)
}