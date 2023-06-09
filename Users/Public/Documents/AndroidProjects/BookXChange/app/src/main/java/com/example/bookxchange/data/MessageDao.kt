package com.example.bookxchange.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MessageDao {
    @Query("SELECT * FROM messages ORDER BY datetime DESC")
    fun getAllMessages(): List<ChatMessage>

    @Insert
    fun addMessage(message: ChatMessage)
}