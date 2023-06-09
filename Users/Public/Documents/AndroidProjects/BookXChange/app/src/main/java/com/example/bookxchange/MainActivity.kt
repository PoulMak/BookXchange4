package com.example.bookxchange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.bookxchange.data.AppDatabase
import com.example.bookxchange.navigation.NavControl
import com.example.bookxchange.navigation.base.BaseScreen
import com.example.bookxchange.presentation.chat.ChatScreen2
import com.example.bookxchange.ui.theme.BookXchangeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "my-db"
        ).build()
        setContent {
            val navController = rememberNavController()
            BookXchangeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    BaseScreen(navController = navController){
                    NavControl(navController = navController)
                    }
                }
            }
        }
    }
}