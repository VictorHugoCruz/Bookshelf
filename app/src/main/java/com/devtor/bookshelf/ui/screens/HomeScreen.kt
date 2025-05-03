package com.devtor.bookshelf.ui.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.devtor.bookshelf.R
import com.devtor.bookshelf.model.BookInfo
import com.devtor.bookshelf.ui.BookshelfUiState
import com.devtor.bookshelf.ui.components.BookshelfAppBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: BookshelfUiState,
    isShowingHomePage: Boolean,
    currentBook: BookInfo? = null,
    onCardClick: (book: BookInfo) -> Unit,
    onBackPressed:()->Unit,
    retryAction: () -> Unit,
) {
    when (uiState) {
        is BookshelfUiState.Loading -> LoadingScreen()
        is BookshelfUiState.Success -> {

            BooksGridScreen(
                books = uiState.books,
                modifier = modifier.fillMaxSize(),
                isShowingHomePage = isShowingHomePage,
                currentBook = currentBook,
                onCardClick = onCardClick,
                onBackPressed = onBackPressed
            )

        }

        is BookshelfUiState.Error -> ErrorScreen(modifier = modifier, retryAction)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksGridScreen(
    books: List<BookInfo>,
    modifier: Modifier = Modifier,
    isShowingHomePage: Boolean,
    currentBook: BookInfo? = null,
    onCardClick: (book: BookInfo) -> Unit,
    onBackPressed: () -> Unit
) {
    val scrollBehavior = if(isShowingHomePage){
        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    }else{
        TopAppBarDefaults.pinnedScrollBehavior()
    }
    Scaffold(
        topBar = {
            BookshelfAppBar(
                title = if (isShowingHomePage) stringResource(R.string.app_name) else currentBook?.title
                    ?: "",
                scrollBehavior = scrollBehavior,
                showingBackArrow = isShowingHomePage,
                onBack = onBackPressed

                )
        }, modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        if (isShowingHomePage) {
            ListBooksContent(
                books = books,
                paddingValues = it,
                modifier = Modifier.padding(4.dp),
                onCardClick = onCardClick
            )
        } else {
            ContentDetail(
                onBackPressed = onBackPressed,
                book = currentBook ?: BookInfo(
                    title = "libro",
                    author = listOf("Autor 1", "Autor 2"),
                    date = "20-12-1999",
                    description = "esta es una breve descripcion de un libro",
                    category = listOf(""),
                    image = "",
                ),
            )
        }
    }
}

@Composable
fun ListBooksContent(
    modifier: Modifier = Modifier,
    books: List<BookInfo>,
    paddingValues: PaddingValues,
    onCardClick: (BookInfo) -> Unit = {}
) {
    LazyVerticalGrid(
        modifier = modifier.padding(paddingValues),
        columns = GridCells.Adaptive(200.dp),

        ) {
        items(books) { book ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .clickable(
                        onClick = {
                            Log.d("Card", "$book")
                            onCardClick(book)
                        }), elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Log.d("Image", book.image)
                val urlImage = "https" + book.image.drop(4)
                AsyncImage(
                    model = urlImage,
                    contentDescription = book.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.aspectRatio(3 / 5f)
                )
            }
        }
    }
}

@Composable
fun ContentDetail(
    modifier: Modifier = Modifier,
    book: BookInfo,
    onBackPressed: () -> Unit
) {
    BackHandler {
        onBackPressed()
    }
    Column(modifier = modifier.verticalScroll(rememberScrollState()).fillMaxSize()) {
        Box(
            modifier = Modifier
                .padding(top = 64.dp)
                .fillMaxWidth()
        ) {
            val urlImage = "https" + book.image.drop(4)
            AsyncImage(
                model = urlImage,
                contentDescription = book.description,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.loading_img),
                error = painterResource(R.drawable.ic_broken_image),
                modifier = Modifier
                    .height(300.dp)
                    .clip(shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.displayMedium,
            )
            Text(
                text = book.date, style = MaterialTheme.typography.titleMedium
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                book.author?.forEach { author ->
                    Text(text = author)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                book.category?.forEach { category ->
                    Text(text = category)
                }
            }
            Text(
                text = book.description ?: "", style = MaterialTheme.typography.bodyLarge
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ContentDetailPreview() {
    MaterialTheme {
        ContentDetail(
            onBackPressed = {},
            book = BookInfo(
                title = "libro",
                author = listOf("Autor 1", "Autor 2"),
                date = "20-12-1999",
                description = "esta es una breve descripcion de un libro",
                category = listOf("tecnologia", "arte"),
                image = "sdfb"
            )
        )
    }
}

