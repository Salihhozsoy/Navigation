package com.example.navigationfirstproject.ui.news

import com.example.navigationfirstproject.entity.News

sealed class NewsListState{
    object Idle: NewsListState()
    object Loading: NewsListState()
    object Empty: NewsListState()
    class Result(val news:MutableList<News>): NewsListState()
    class Error(val throwable: Throwable): NewsListState()
}
