package com.example.bookxchange.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bookxchange.ui.theme.Grey1
import com.example.bookxchange.ui.theme.Purple1
import com.example.bookxchange.ui.theme.Purple4
import com.example.bookxchange.ui.theme.Yellow1

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val isBottomBar = listOf("Search","Info", "Map","ChatL","Chat").any {it == navBackStackEntry?.destination?.route}
    var selected = remember {
        mutableStateOf(1)
    }
    AnimatedVisibility(visible = isBottomBar) {
        NavigationBar(containerColor = Purple4, modifier = Modifier.height(60.dp) .shadow(15.dp)) {
            NavigationBarItem(
                selected = selected.value == 1,
                onClick = {
                    selected.value = 1
                    navController.navigate("Search")
                },
                icon = {
                    Icon(
                        Icons.Default.Search,
                        null
                    )
                })
            NavigationBarItem(
                selected = selected.value == 3,
                onClick = {
                    selected.value = 3
                    navController.navigate("Main")
                },
                icon = {
                    Icon(
                        Icons.Default.Home,
                        null
                    )
                })
            NavigationBarItem(
                selected = selected.value == 4,
                onClick = {
                    selected.value = 4
                    navController.navigate("Map")
                },
                icon = {
                    Icon(
                        Icons.Default.Map,
                        null
                    )
                })
            NavigationBarItem(
                selected = selected.value == 5,
                onClick = {
                    selected.value = 5
                    navController.navigate("ChatL")
                },
                icon = {
                    Icon(
                        Icons.Default.Chat,
                        null
                    )
                })
        }
    }
}
