package com.example.navigationfirstproject.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.navigationfirstproject.entity.News
import com.example.navigationfirstproject.entity.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)


    @Query("SELECT * FROM User where email =:email and password=:password")
    suspend fun getUser(email:String, password:String):User?

    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun getUserById(id: Int): User

    @Delete
    suspend fun delete(news: News)
}