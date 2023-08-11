package com.example.navigationfirstproject.ui.book

import com.example.navigationfirstproject.entity.Book

sealed class BookState {
    object Idle:BookState()
    object Success:BookState()
    object Error:BookState()
}