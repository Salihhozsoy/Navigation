package com.example.navigationfirstproject.ui.author

import com.example.navigationfirstproject.entity.Author

sealed class AuthorState {
    object Idle:AuthorState()

    class Success(val author: Author):AuthorState()
    class Error(val throwable: Throwable):AuthorState()
}