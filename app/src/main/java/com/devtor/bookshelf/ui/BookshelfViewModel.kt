package com.devtor.bookshelf.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import com.devtor.bookshelf.BookshelfApplication
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil3.network.HttpException
import com.devtor.bookshelf.data.BookshelfRepository
import com.devtor.bookshelf.model.BookInfo
import kotlinx.coroutines.launch

sealed interface BookshelfUiState {
    data class Success(val books: List<BookInfo>) : BookshelfUiState
    object Loading : BookshelfUiState
    object Error : BookshelfUiState
}

class BookshelfViewModel(
    val bookshelfRepository: BookshelfRepository
) : ViewModel() {
    var uiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
        private set

    var isShowingHomepage by mutableStateOf(true)
        private set

    var currentBook: BookInfo? = null
        private set

    init {
        loadBooks()
    }

    fun loadBooks() {
        viewModelScope.launch {
            try {
                val books = bookshelfRepository.getBookshelfData()
                uiState = BookshelfUiState.Success(books)
            } catch (e: Exception) {
                Log.e("Error", "$e")
                uiState = BookshelfUiState.Error
            } catch (e: HttpException) {
                Log.e("Error", "$e")
                uiState = BookshelfUiState.Error
            }
        }

    }

    fun showingDetailScreen(book: BookInfo) {
        isShowingHomepage = !isShowingHomepage
        currentBook = book
    }

    fun onBack() {
        isShowingHomepage = !isShowingHomepage
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val bookshelfRepository = application.container.bookshelfRepository
                BookshelfViewModel(bookshelfRepository = bookshelfRepository)
            }
        }
    }
}