package com.example.navigationfirstproject.ui.author

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationfirstproject.AppDatabase
import com.example.navigationfirstproject.entity.Author
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthorFragmentViewModel : ViewModel() {

    private var _authorState: MutableStateFlow<AuthorState> = MutableStateFlow(AuthorState.Idle)
    var authorState: StateFlow<AuthorState> = _authorState

    fun addAuthor(appDatabase: AppDatabase, name: String, surname: String) {
        viewModelScope.launch {
            runCatching {
                val author = Author(name = name, surname = surname)
                appDatabase.authorDao().insert(author)
                _authorState.value=AuthorState.Success(author)
            }.onFailure {
                _authorState.value=AuthorState.Error(it)
            }
        }
    }
}
