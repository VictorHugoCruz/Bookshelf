package com.devtor.bookshelf.ui

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.devtor.bookshelf.R
import com.devtor.bookshelf.model.BookInfo
import com.devtor.bookshelf.ui.components.BookshelfAppBar

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
fun ErrorScreen(modifier: Modifier = Modifier) {
    Text(text = "Error")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksGridScreen(books: List<BookInfo>, modifier: Modifier = Modifier) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        topBar = {
            BookshelfAppBar(
                title = stringResource(R.string.app_name),
                scrollBehavior = scrollBehavior
            )
        },
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        ListBooksContent(books = books, paddingValues = it, modifier = Modifier.padding(4.dp))
    }
}

@Composable
fun ListBooksContent(modifier: Modifier = Modifier,books: List<BookInfo>, paddingValues: PaddingValues) {
    LazyVerticalGrid(
        modifier = modifier.padding(paddingValues),
        columns = GridCells.Adaptive(200.dp),

        ) {
        items(books) { book ->
            Card(
                modifier = Modifier.fillMaxSize().padding(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Log.d("Image", book.image)
                val urlImage = "https"+book.image.drop(4)
                AsyncImage(
                    model = urlImage,
                    contentDescription = book.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.aspectRatio(3/5f)
                )
            }
        }
    }
}

