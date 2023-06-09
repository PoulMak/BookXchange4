package com.example.bookxchange.navigation

import InfoScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookxchange.data.AppDatabase
import com.example.bookxchange.map.presentation.MapScreen
import com.example.bookxchange.presentation.auth.AuthScreen
import com.example.bookxchange.presentation.chat.ChatList
import com.example.bookxchange.presentation.chat.ChatScreen
import com.example.bookxchange.presentation.chat.ChatScreen2
import com.example.bookxchange.presentation.main.MainScreen
import com.example.bookxchange.presentation.search.SearchScreen


@Composable
fun NavControl(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "Main"
    ) {

        composable(route = "Search") {
            SearchScreen(navController = navController)
        }

        composable(route = "ChatL") {
            ChatList(navController = navController)
        }

        composable(
            route = "Info?bookId={bookId}",
            arguments = listOf(
                navArgument(
                    name = "bookId"
                ) {
                    type = NavType.IntType
                },
            )
        ) {
            InfoScreen(navController = navController)
        }

        composable("Chat") {
            ChatScreen(navController = navController)
        }

        composable("Main") {
            MainScreen(navController = navController)
        }

        composable("Map") {
            MapScreen(navController = navController)
        }
        composable("Auth") {
            AuthScreen(navController = navController)
        }
    }
}