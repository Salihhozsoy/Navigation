package com.example.navigationfirstproject.ui.bookdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationfirstproject.AppDatabase
import com.example.navigationfirstproject.ui.book.GetAuthorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class BookDetailViewModel:ViewModel() {

    private var _getBookAuthorState: MutableStateFlow<GetBookAuthorState> = MutableStateFlow(GetBookAuthorState.Idle)
    var getBookAuthorState: StateFlow<GetBookAuthorState> = _getBookAuthorState



    fun getBooks(appDatabase: AppDatabase){
        viewModelScope.launch {
            kotlin.runCatching {
                val books = appDatabase.bookDao().getAll()
                _getBookAuthorState.value = if (books.isEmpty()) GetBookAuthorState.Empty else GetBookAuthorState.Success(books)
            }.onFailure {
                _getBookAuthorState.value = GetBookAuthorState.Error
            }
        }
    }
}