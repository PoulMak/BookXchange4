package com.example.bookxchange.map.presentation

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ovh.plrapps.mapcompose.ui.MapUI

@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { viewModel.addMarker(navController) }) {
            Icon(imageVector = Icons.Default.AddLocationAlt,contentDescription = null)
        }
    }) { padding ->
        MapUI(state = viewModel.mapState, modifier = Modifier.padding(padding))
    }
}

