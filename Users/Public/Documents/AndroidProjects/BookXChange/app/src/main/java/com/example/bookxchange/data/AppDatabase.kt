package com.example.bookxchange.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ChatMessage::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private lateinit var INSTANCE:AppDatabase
        fun getInstance(context: Context):AppDatabase= Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "appdatabase"

        )

            .createFromAsset("appdatabase.db")
            .allowMainThreadQueries()
            .build()

    }
    abstract fun messageDao(): MessageDao
}