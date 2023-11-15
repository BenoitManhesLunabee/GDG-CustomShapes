package com.lunabee.customshapes.demo.shapeutils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

/**
 * Create a elliptic shape on the bottom.
 * This shape is the intersect between the initial rectangle shape and an inscribed ellipse.
 * The 2 ratio ellipseWidthRatio and ellipseHeightRatio define the curve based on initial width
 * @param ellipseWidthRatio define the ellipse width / initial width ratio. It can not be < 1
 * @param ellipseHeightRatio define the ellipse height / initial width ratio
 */
class BottomEllipticShape(
    private val ellipseWidthRatio: Float,
    private val ellipseHeightRatio: Float,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val ellipseWidth = size.width * java.lang.Float.max(1f, ellipseWidthRatio)
        val ellipseHeight = size.width * ellipseHeightRatio
        val ellipseSize = Size(width = ellipseWidth, height = ellipseHeight)
        return Outline.Generic(
            path = drawEllipsePath(size, ellipseSize),
        )
    }

    private fun drawEllipsePath(size: Size, ellipseSize: Size): Path {
        val rectToMiddleEllipsePath = Path().apply {
            addRect(
                rect = Rect(
                    offset = Offset.Zero,
                    size = Size(
                        width = size.width,
                        height = size.height - (ellipseSize.height / 2),
                    ),
                ),
            )
        }

        val ellipseOffset = Offset(
            x = -(ellipseSize.width - size.width) / 2,
            y = (size.height - ellipseSize.height),
        )
        val ellipsePath = Path().apply {
            addOval(
                oval = Rect(
                    offset = ellipseOffset,
                    size = ellipseSize,
                ),
            )
        }
        return Path().apply { op(ellipsePath, rectToMiddleEllipsePath, PathOperation.Union) }
    }
}