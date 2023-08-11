package com.example.navigationfirstproject.ui.bookdetail

sealed class GetBookAuthorState{
    object Idle:GetBookAuthorState()
    object Empty:GetBookAuthorState()
    class Success(val books:List<BookAuthorModel>):GetBookAuthorState()
    object Error:GetBookAuthorState()
}
