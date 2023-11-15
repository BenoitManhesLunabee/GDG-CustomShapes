package com.lunabee.customshapes.demo.shapeutils

import androidx.annotation.FloatRange
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.min

class PinShape(
    private val tipHeightRatio: Float = 1f / 3,
    private val tipWidthRatio: Float = 9f / 5,
    @FloatRange(0.0, 1.0) private val tipHeightProgress: Float = 1f,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val tipHeight = size.height * tipHeightRatio
        val tipSize = Size(
            width = tipHeight * tipWidthRatio,
            height = tipHeight * tipHeightProgress,
        )
        val containerSize = size.copy(
            height = size.height - tipHeight,
        )
        val containerCornerRadius = min(
            containerSize.height,
            (size.width - tipSize.width) / 2,
        )

        return Outline.Generic(
            path = drawPinPath(
                containerSize = containerSize,
                containerCornerRadius = containerCornerRadius,
                tipSize = tipSize,
            ),
        )
    }

    private fun drawPinPath(containerSize: Size, containerCornerRadius: Float, tipSize: Size): Path {
        val containerPath = Path().apply {
            addRoundRect(
                RoundRect(
                    rect = Rect(offset = Offset.Zero, size = containerSize),
                    cornerRadius = CornerRadius(containerCornerRadius),
                ),
            )
        }
        val tipPath = Path().apply {
            moveTo(
                x = containerSize.width / 2 - tipSize.width / 2,
                y = containerSize.height,
            )
            relativeLineTo(tipSize.width, 0f)
            relativeLineTo(-tipSize.width / 2, tipSize.height)
            close()
        }
        return Path.combine(operation = PathOperation.Union, containerPath, tipPath)
    }
}