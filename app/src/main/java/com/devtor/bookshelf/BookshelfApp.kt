package com.devtor.bookshelf

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devtor.bookshelf.ui.BookshelfViewModel
import com.devtor.bookshelf.ui.screens.HomeScreen

@Composable
fun BookshelfApp(modifier: Modifier = Modifier) {
    val viewModel: BookshelfViewModel = viewModel(
        factory = BookshelfViewModel.Factory
    )
    val uiState = viewModel.uiState
    HomeScreen(
        uiState = uiState,
        isShowingHomePage = viewModel.isShowingHomepage,
        currentBook = viewModel.currentBook,
        onCardClick = { viewModel.showingDetailScreen(it) },
        onBackPressed = {viewModel.onBack()},
        modifier = modifier
    )

}



