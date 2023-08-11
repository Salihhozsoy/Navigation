package com.example.navigationfirstproject.ui.book

import com.example.navigationfirstproject.entity.Author
import com.example.navigationfirstproject.ui.author.AuthorState

sealed class GetAuthorState{
    object Idle:GetAuthorState()
    object Empty: GetAuthorState()
    class Success(val author: List<Author>):GetAuthorState()
    class Error(val throwable: Throwable):GetAuthorState()
}
