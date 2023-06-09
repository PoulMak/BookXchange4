package com.example.bookxchange.map.presentation

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.app.bookxchange.R
import com.example.bookxchange.map.data.MapApi
import com.example.bookxchange.presentation.search.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ovh.plrapps.mapcompose.api.addLayer
import ovh.plrapps.mapcompose.api.addMarker
import ovh.plrapps.mapcompose.api.centroidX
import ovh.plrapps.mapcompose.api.centroidY
import ovh.plrapps.mapcompose.api.enableFlingZoom
import ovh.plrapps.mapcompose.api.enableMarkerDrag
import ovh.plrapps.mapcompose.api.enableRotation
import ovh.plrapps.mapcompose.api.enableScrolling
import ovh.plrapps.mapcompose.api.enableZooming
import ovh.plrapps.mapcompose.api.minScale
import ovh.plrapps.mapcompose.api.scale
import ovh.plrapps.mapcompose.api.scrollTo
import ovh.plrapps.mapcompose.api.shouldLoopScale
import ovh.plrapps.mapcompose.core.TileStreamProvider
import ovh.plrapps.mapcompose.ui.layout.MinimumScaleMode
import ovh.plrapps.mapcompose.ui.state.MapState
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val mapApi: MapApi,
) : ViewModel() {
    private val tileStreamProvider = makeTileStreamProvider()
    var markerCount =0


    var mapState: MapState by mutableStateOf(
        /* Notice how we increase the worker count when performing HTTP requests */
        MapState(
            levelCount = 20,
            fullWidth = 134217728,
            fullHeight = 134217728,
            workerCount = 16,
            initialValuesBuilder = {
                maxScale(5f)
            }
        ).apply {
            addLayer(tileStreamProvider)
            enableZooming()
            enableFlingZoom()
            enableScrolling()
            viewModelScope.launch {
                scrollTo(x = 0.6045, y = 0.3126, destScale = 10f)
            }
            shouldLoopScale = true

        }
    )

    private fun makeTileStreamProvider() =
        TileStreamProvider { row, col, zoomLvl ->
            Log.d("scale: ", zoomLvl.toString())
            val responseBody =
                mapApi.getTileByApi(row = row, col = col, scale = zoomLvl).execute().body()
            responseBody?.byteStream()
        }
    fun addMarker(navController: NavHostController) {
        mapState.addMarker("marker$markerCount", mapState.centroidX, mapState.centroidY) {
            Icon(
                imageVector = Icons.Default.MenuBook,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clickable { navController.navigate("Info?bookId=${markerCount% Book.ALL.size}") },
                tint = Color(0xCC2196F3)
            )
        }
        mapState.enableMarkerDrag("marker$markerCount")
        markerCount++
    }

}
