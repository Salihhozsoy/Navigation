package com.example.navigationfirstproject.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationfirstproject.AdapterState
import com.example.navigationfirstproject.AppDatabase
import com.example.navigationfirstproject.entity.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SecondFragmentViewModel:ViewModel() {

    private val _newsListState: MutableStateFlow<NewsListState> = MutableStateFlow(NewsListState.Idle)
    val newsListState: StateFlow<NewsListState> = _newsListState

    private val _adapterState:MutableStateFlow<AdapterState> = MutableStateFlow(AdapterState.Idle)
    val adapterState:StateFlow<AdapterState> = _adapterState

    fun getNews(appDatabase: AppDatabase){
        viewModelScope.launch {
            runCatching {
                _newsListState.value = NewsListState.Loading
                val news = appDatabase.newsDao().getNews()
                _newsListState.value = if (news.isEmpty()) NewsListState.Empty else NewsListState.Result(news.toMutableList()
                )
            }.onFailure {
                _newsListState.value = NewsListState.Error(it)
            }
        }
    }

    fun removeNews(appDatabase: AppDatabase, news: News){
        viewModelScope.launch {
            if (_newsListState.value is NewsListState.Result){     //bu liste bu listeyse diyoruz
                val index = (_newsListState.value as NewsListState.Result).news.indexOf(news)   //result olarak bu işi yap demek as
                (_newsListState.value as NewsListState.Result).news.remove(news)     //görünen listede sildik
                appDatabase.userDao().delete(news)           //silme işlemi aslında burası ama liste güncelleme için üsttekini yapıyoruz
                _adapterState.value = AdapterState.Removed(index)
            }
        }
    }
}