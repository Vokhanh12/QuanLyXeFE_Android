package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.manager

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.NoOpUpdate
import androidx.core.graphics.drawable.toBitmap
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.R
import com.mapbox.bindgen.Value
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager


@Composable
fun MapBoxMap(
    modifier: Modifier = Modifier,
    point: Point?,
) {
    val context = LocalContext.current
    val originalMarker = remember(context) {
        context.getDrawable(R.drawable.marker)?.toBitmap()
    }


    // Kích thước mới của marker
    val resizedMarkerSize = 300

    // Thay đổi kích thước của bitmap
    val resizedMarker = remember(originalMarker, resizedMarkerSize) {
        originalMarker?.let {
            Bitmap.createScaledBitmap(it, resizedMarkerSize, resizedMarkerSize, false)
        }
    }

    var _statusModelMap by remember {
        mutableStateOf("day")
    }

    var pointAnnotationManager: PointAnnotationManager? by remember {
        mutableStateOf(null)
    }

    Box{


        AndroidView(
            factory = {
                MapView(it).also { mapView ->
                    mapView.getMapboxMap().loadStyle(Style.STANDARD){ style ->
                        style.setStyleImportConfigProperty("basemap", "lightPreset", Value.valueOf("$_statusModelMap "))
                    }
                    val annotationApi = mapView.annotations
                    pointAnnotationManager = annotationApi.createPointAnnotationManager()
                }
            },
            update = { mapView ->
                if (point != null) {
                    pointAnnotationManager?.let {
                        it.deleteAll()
                        val pointAnnotationOptions = PointAnnotationOptions()
                            .withPoint(point)
                            .withIconImage(resizedMarker!!)

                        it.create(pointAnnotationOptions)
                        mapView.getMapboxMap()
                            .flyTo(CameraOptions.Builder().zoom(10.0).center(point).build())
                    }
                }
                NoOpUpdate
            },
            modifier = modifier
        )
        
        Row{
            Button(onClick = {
                _statusModelMap = "day"
            }) {
                Text(text = "Light")
            }
            
            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = {
                _statusModelMap = "night"
            }) {
                Text(text = "Night")
            }
        }

        

    }


}

@Composable
fun MapScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        MapBoxMap(
            point = Point.fromLngLat(106.67992, 10.36076),
            modifier = Modifier
                .fillMaxSize()
        )
    }
}