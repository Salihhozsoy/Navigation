package com.example.navigationfirstproject.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.navigationfirstproject.entity.Author


@Dao
interface AuthorDao {

    @Insert
    suspend fun insert(author: Author)

    @Query("SELECT * FROM Author")
    suspend fun getAll():List<Author>

    @Query("SELECT * FROM Author where name =:name and surname=:surname")
    suspend fun getAuthor(name:String, surname:String): Author?

    @Query("SELECT * FROM Author WHERE id = :id")
    suspend fun getAuthorById(id: Int): Author

    @Delete
    suspend fun delete(author: Author)
}