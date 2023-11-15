package com.lunabee.customshapes.demo.shapeutils

import android.graphics.Matrix
import android.graphics.RectF
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.AndroidPath
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.graphics.shapes.RoundedPolygon
import kotlin.math.abs
import kotlin.math.min

class RoundedPolygonShape(
    val roundedPolygon: RoundedPolygon,
) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val sizedRoundedPolygon = resizeMaybe(roundedPolygon, size)
        val path = Path().apply {
            addPath(AndroidPath(sizedRoundedPolygon.toPath()))
        }
        return Outline.Generic(path)
    }

    private fun resizeMaybe(roundedPolygon: RoundedPolygon, newSize: Size): RoundedPolygon {
        val width = roundedPolygon.bounds.width()
        val height = roundedPolygon.bounds.height()
        if (abs(width - newSize.width) > 1e-4 || abs(height - newSize.height) > 1e-4) {
            val matrix = calculateMatrix(roundedPolygon.bounds, newSize.width, newSize.height)
            roundedPolygon.transform(matrix)
        }
        return roundedPolygon
    }
}

internal fun calculateMatrix(bounds: RectF, width: Float, height: Float): Matrix {
    val originalWidth = bounds.right - bounds.left
    val originalHeight = bounds.bottom - bounds.top
    val scale = min(width / originalWidth, height / originalHeight)
    val newLeft = bounds.left - (width / scale - originalWidth) / 2
    val newTop = bounds.top - (height / scale - originalHeight) / 2
    val matrix = Matrix()
    matrix.setTranslate(-newLeft, -newTop)
    matrix.postScale(scale, scale)
    return matrix
}