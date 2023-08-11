package com.example.navigationfirstproject.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.navigationfirstproject.entity.News

@Dao
interface NewsDao {

    @Insert
    suspend fun insert(news: News)

    @Query("SELECT * FROM News")
    suspend fun getNews():List<News>

    @Query("SELECT * FROM News WHERE id = :id")
    suspend fun getNewById(id: Int): News


}