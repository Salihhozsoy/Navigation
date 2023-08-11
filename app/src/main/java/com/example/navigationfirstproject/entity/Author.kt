package com.example.navigationfirstproject.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Author(
    @PrimaryKey(autoGenerate = true) val id:Int=0,
    val name:String?,
    val surname:String?,

)
