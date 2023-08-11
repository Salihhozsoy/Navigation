package com.example.navigationfirstproject.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

import com.example.navigationfirstproject.entity.Book
import com.example.navigationfirstproject.ui.bookdetail.BookAuthorModel
import com.example.navigationfirstproject.ui.bookdetail.BookDetailViewModel

@Dao
interface BookDao {

    @Insert
    suspend fun insert(book: Book)

    @Query("SELECT Book.name as 'bookName',Book.id as 'bookId' ,Author.name as 'authorName', Author.surname as 'authorSurname', Book.imageUrl from Book,Author where authorId=Author.id" )
    suspend fun getAll(): List<BookAuthorModel>

    @Query("SELECT * FROM Book WHERE id = :id")
    suspend fun getBookById(id: Int): Book

    @Delete
    suspend fun delete(book: Book)
}

