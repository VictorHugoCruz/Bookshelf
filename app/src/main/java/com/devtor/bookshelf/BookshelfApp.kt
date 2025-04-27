package com.devtor.bookshelf

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devtor.bookshelf.ui.BookshelfViewModel
import com.devtor.bookshelf.ui.HomeScreen

@Composable
fun BookshelfApp(modifier: Modifier = Modifier) {
    val viewModel: BookshelfViewModel = viewModel(
        factory = BookshelfViewModel.Factory
    )
    val uiState = viewModel.uiState
    HomeScreen(
        uiState = uiState,
        modifier = modifier
    )

}



