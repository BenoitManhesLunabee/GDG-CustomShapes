package com.lunabee.customshapes.demo.shapeutils

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.AndroidPath
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.graphics.shapes.Morph
import kotlin.math.abs

class MorphShape(
    val morph: Morph,
) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val morphResized = resizeMaybe(morph, size)
        val path = morphResized.asPath().asComposePath()
        return Outline.Generic(path)
    }

    private fun resizeMaybe(morph: Morph, newSize: Size): Morph {
        val width = morph.bounds.width()
        val height = morph.bounds.height()
        if (abs(width - newSize.width) > 1e-4 || abs(height - newSize.height) > 1e-4) {
            val matrix = calculateMatrix(morph.bounds, newSize.width, newSize.height)
            morph.transform(matrix)
        }
        return morph
    }
}

class MorphShape2(
    val morph: Morph,
) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val morphResized = resizeMaybe(morph, size)
        val path = Path().apply {
            addPath(AndroidPath(morphResized.asPath()))
        }
        return Outline.Generic(path)
    }

    private fun resizeMaybe(morph: Morph, newSize: Size): Morph {
        val width = morph.bounds.width()
        val height = morph.bounds.height()
        if (abs(width - newSize.width) > 1e-4 || abs(height - newSize.height) > 1e-4) {
            val matrix = calculateMatrix(morph.bounds, newSize.width, newSize.height)
            morph.transform(matrix)
        }
        return morph
    }
}
