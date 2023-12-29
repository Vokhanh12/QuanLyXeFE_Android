package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.manager

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.NoOpUpdate
import androidx.core.graphics.drawable.toBitmap
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.R
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.theme.BlueSky
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.theme.BorderColor
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.theme.NightSky
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
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.util.lerp
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.theme.DarkModeSwitchTheme


@Composable
fun MapBoxMap(
    modifier: Modifier = Modifier,
    point: Point?,
) {
    val context = LocalContext.current
    val originalMarker = remember(context) {
        context.getDrawable(R.drawable.marker)?.toBitmap()
    }

    val resizedMarkerSize = 300
    val resizedMarker = remember(originalMarker, resizedMarkerSize) {
        originalMarker?.let {
            Bitmap.createScaledBitmap(it, resizedMarkerSize, resizedMarkerSize, false)
        }
    }

    var nightMode by remember {
        mutableStateOf(true)
    }

    var pointAnnotationManager: PointAnnotationManager? by remember {
        mutableStateOf(null)
    }

    Box {
        AndroidView(
            factory = {

                MapView(it).also { mapView ->
                    pointAnnotationManager = mapView.annotations.createPointAnnotationManager()

                    if (point != null) {
                        pointAnnotationManager?.let {
                            it.deleteAll()
                            val pointAnnotationOptions = PointAnnotationOptions()
                                .withPoint(point)
                                .withIconImage(resizedMarker!!)

                            it.create(pointAnnotationOptions)
                            mapView.getMapboxMap()
                                .flyTo(CameraOptions.Builder().bearing(45.0).zoom(10.0).pitch(50.0).center(point).build())
                        }
                    }
                }

            },
            update = { mapView ->



                mapView.getMapboxMap().loadStyle(Style.STANDARD) { style ->
                    val lightPreset = if (nightMode) "day" else "night"
                    style.setStyleImportConfigProperty("basemap", "lightPreset", Value.valueOf(lightPreset))
                }

                Log.d("MapBoxMap", "Updating map with nightMode: $nightMode")



                NoOpUpdate
            },
            modifier = modifier
        )

        Row {

            DarkModeSwitch{ status ->
                nightMode = status
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



@Composable
fun DarkModeSwitch(checked: Boolean, modifier: Modifier, onCheckedChanged: (Boolean) -> Unit) {

    val switchWidth = 160.dp
    val switchHeight = 64.dp
    val handleSize = 52.dp
    val handlePadding = 10.dp

    val valueToOffset = if (checked) 1f else 0f
    val offset = remember { androidx.compose.animation.core.Animatable(valueToOffset) }
    val scope = rememberCoroutineScope()

    DisposableEffect(checked) {
        if (offset.targetValue != valueToOffset) {
            scope.launch {
                offset.animateTo(valueToOffset, animationSpec = tween(1000))
            }
        }
        onDispose { }
    }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .width(switchWidth)
            .height(switchHeight)
            .clip(RoundedCornerShape(switchHeight))
            .background(lerp(BlueSky, NightSky, offset.value))
            .border(3.dp, BorderColor, RoundedCornerShape(switchHeight))
            .toggleable(
                value = checked,
                onValueChange = onCheckedChanged,
                role = Role.Switch,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
    ) {
        val backgroundPainter = painterResource(R.drawable.background)
        Canvas(modifier = Modifier.fillMaxSize()) {
            with(backgroundPainter) {
                val scale = size.width / intrinsicSize.width
                val scaledHeight = intrinsicSize.height * scale
                translate(top = (size.height - scaledHeight) * (1f - offset.value)) {
                    draw(Size(size.width, scaledHeight))
                }
            }
        }

        Image(
            painter = painterResource(R.drawable.glow),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(switchWidth)
                .graphicsLayer {
                    scaleX = 1.2f
                    scaleY = scaleX
                    translationX = lerp(
                        -size.width * 0.5f + handlePadding.toPx() + handleSize.toPx() * 0.5f,
                        switchWidth.toPx() - size.width * 0.5f - handlePadding.toPx() - handleSize.toPx() * 0.5f,
                        offset.value
                    )
                }
        )

        Box(
            modifier = Modifier
                .padding(horizontal = handlePadding)
                .size(handleSize)
                .offset(x = (switchWidth - handleSize - handlePadding * 2f) * offset.value)
                .paint(painterResource(R.drawable.sun))
                .clip(CircleShape)
        ) {
            Image(
                painter = painterResource(R.drawable.moon),
                contentDescription = null,
                modifier = Modifier
                    .size(handleSize)
                    .graphicsLayer {
                        translationX = size.width * (1f - offset.value)
                    }
            )
        }
    }
}

@Composable
fun DarkModeSwitch(getStatus:(Boolean) -> Unit) {

    var status by remember { mutableStateOf(true) }

    // Khi giá trị selectedText thay đổi, gọi lambda expression để thông báo giá trị mới
    DisposableEffect(status) {
        onDispose {
            getStatus(status)
        }
    }

    DarkModeSwitchTheme {
        Column {
            DarkModeSwitch(!status, Modifier.padding(24.dp)) { status = !it }
        }
    }
}