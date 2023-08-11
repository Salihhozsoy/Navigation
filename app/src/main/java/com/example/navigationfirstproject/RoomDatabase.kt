package com.example.navigationfirstproject

import android.content.Context
import androidx.room.Room

object RoomDatabase {
    fun getDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java,"database").build()
}