package com.jihan.cleanarchitecture.presentation

import android.graphics.Bitmap
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import coil3.ImageLoader
import coil3.compose.SubcomposeAsyncImage
import coil3.request.crossfade
import com.jihan.cleanarchitecture.domain.model.ImageModel

@Composable
fun SetWallpaper(imageModel: ImageModel) {
    val context = LocalContext.current
    val scale = remember { mutableFloatStateOf(1f) }
    var pivotX by remember { mutableFloatStateOf(0.5f) }
    var pivotY by remember { mutableFloatStateOf(0.5f) }

    var bitmap: Bitmap? = null

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        SubcomposeAsyncImage(
            model = imageModel.imageUrl,
            contentDescription = imageModel.description,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.9f)
                .pointerInput(Unit) {
                    detectTapGestures(onDoubleTap = { offset ->
                        scale.floatValue = if (scale.floatValue == 1f) 3f else 1f
                        pivotX = offset.x / size.width
                        pivotY = offset.y / size.height
                    })
                }
                .graphicsLayer(
                    scaleX = scale.floatValue,
                    scaleY = scale.floatValue,
                    transformOrigin = TransformOrigin(pivotX, pivotY)
                ),
            imageLoader = ImageLoader(context).newBuilder().crossfade(true).build(),
            loading = {
                Box(Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            },

            )


    }
}






