package com.example.bookxchange.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class ChatMessage(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val senderName: String,
    val datetime: String,
    val message: String
)