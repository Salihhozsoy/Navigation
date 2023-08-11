package com.example.navigationfirstproject.ui.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationfirstproject.AppDatabase
import com.example.navigationfirstproject.entity.Author
import com.example.navigationfirstproject.entity.Book
import com.example.navigationfirstproject.ui.author.AuthorState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookFragmentViewModel : ViewModel() {

    private val _bookState: MutableSharedFlow<BookState> = MutableSharedFlow()
    val bookState: SharedFlow<BookState> = _bookState

    private var _getAuthorState: MutableStateFlow<GetAuthorState> = MutableStateFlow(GetAuthorState.Idle)
    var getAuthorState: StateFlow<GetAuthorState> = _getAuthorState

    private val selectedAuthor: MutableStateFlow<Author?> = MutableStateFlow(null)

    fun addBook(appDatabase: AppDatabase, name: String, pageNumber: String, publisherName: String) {
        viewModelScope.launch {
            if (name.isEmpty() && pageNumber.isEmpty() && publisherName.isEmpty()) return@launch
            selectedAuthor.value?.let {
                val book = Book(
                    name = name,
                    pageNumber = pageNumber.toInt(),
                    publisherName = publisherName,
                    authorId = it.id
                )
                appDatabase.bookDao().insert(book)
                _bookState.emit(BookState.Success)
            } ?: kotlin.run {
                _bookState.emit(BookState.Error)
            }
        }
    }

    fun getAuthors(appDatabase: AppDatabase) {
        viewModelScope.launch {
            kotlin.runCatching {
                val authors = appDatabase.authorDao().getAll()
                _getAuthorState.value =
                    if (authors.isEmpty()) GetAuthorState.Empty else GetAuthorState.Success(authors)
            }.onFailure {
                _getAuthorState.value = GetAuthorState.Error(it)
            }
        }
    }

    fun authorSelected(position: Int) {
        viewModelScope.launch {
            if (_getAuthorState.value is GetAuthorState.Success) {
                val author = (_getAuthorState.value as GetAuthorState.Success).author[position]
                selectedAuthor.value = author
            }
        }
    }
}