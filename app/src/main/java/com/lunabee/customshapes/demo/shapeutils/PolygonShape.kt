package com.lunabee.customshapes.demo.shapeutils

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.lunabee.customshapes.demo.utils.polygon

class PolygonShape(
    private val sides: Int,
) : Shape {
    override fun createOutline(
        size: Size, layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        return Outline.Generic(
            path = Path().apply {
                polygon(
                    sides = sides,
                    radius = size.minDimension / 2,
                    center = size.center,
                )
            },
        )
    }
}
