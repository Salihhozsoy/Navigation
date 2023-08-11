package com.example.navigationfirstproject

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.navigationfirstproject.dao.AuthorDao
import com.example.navigationfirstproject.dao.BookDao
import com.example.navigationfirstproject.dao.NewsDao
import com.example.navigationfirstproject.dao.UserDao
import com.example.navigationfirstproject.entity.Author
import com.example.navigationfirstproject.entity.Book
import com.example.navigationfirstproject.entity.News
import com.example.navigationfirstproject.entity.User

@Database(
    entities = [User::class, News::class, Book::class, Author::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun newsDao(): NewsDao
    abstract fun authorDao(): AuthorDao
    abstract fun bookDao(): BookDao
}