package com.example.bookxchange.presentation.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.bookxchange.controller.BottomBar

@Composable
fun BaseScreen(
    navController: NavHostController,
    content: @Composable () -> Unit
) {
    Scaffold(bottomBar = { BottomBar(navController = navController) }) { paddingValues ->
        Box(
            Modifier.padding(paddingValues)
        ) {
            content()
        }

    }
}

