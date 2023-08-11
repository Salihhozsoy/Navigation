package com.example.navigationfirstproject.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class News(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String?,
    val description: String?,
    val imageUrl:String?,
    val postTime:String?
)

