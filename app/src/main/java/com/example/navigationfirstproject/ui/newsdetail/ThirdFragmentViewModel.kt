package com.example.navigationfirstproject.ui.newsdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationfirstproject.AppDatabase
import com.example.navigationfirstproject.entity.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ThirdFragmentViewModel:ViewModel() {

    private val _newsState: MutableStateFlow<News?> = MutableStateFlow(null)
    val newsState: StateFlow<News?> = _newsState


    fun getNewsById(id: Int, appDatabase: AppDatabase) {
        viewModelScope.launch {
            runCatching {
                val news = appDatabase.newsDao().getNewById(id)
                _newsState.value = news
            }
        }
    }
}