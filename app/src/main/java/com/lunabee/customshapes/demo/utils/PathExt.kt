package com.lunabee.customshapes.demo.utils

import android.graphics.Matrix
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath

fun Path.transform(scaleX: Float = 1f, scaleY: Float = 1f): Path {
    val matrix = Matrix().apply {
        postScale(scaleX, scaleY)
    }
    val androidTransformPath = this.asAndroidPath().apply {
        transform(matrix)
    }
    return androidTransformPath.asComposePath()
}

fun Path.polygon(sides: Int, radius: Float, center: Offset) {
    val angle = 2.0 * Math.PI / sides
    moveTo(
        x = center.x + (radius * Math.cos(0.0)).toFloat(),
        y = center.y + (radius * Math.sin(0.0)).toFloat()
    )
    for (i in 1 until sides) {
        lineTo(
            x = center.x + (radius * Math.cos(angle * i)).toFloat(),
            y = center.y + (radius * Math.sin(angle * i)).toFloat()
        )
    }
    close()
}