package com.example.navigationfirstproject.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(
    ForeignKey(
        entity = Author::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("authorId"),
        onDelete = CASCADE
    )
))
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String?,
    val pageNumber: Int? = 0,
    val publisherName: String?,
    val authorId: Int,
    val imageUrl:String? =null
)
