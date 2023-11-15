package com.lunabee.customshapes.demo.cutout

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import com.lunabee.customshapes.demo.utils.transform

class CutoutShape(
    private val cuttingPath: Path,
    private val cuttingSpacing: Dp,
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val (cuttingSizePx, cuttingSpacingPx) = with(density) {
            cuttingPath.getBounds() to cuttingSpacing.toPx()
        }
        val scaleX = (cuttingSizePx.width + cuttingSpacingPx) / cuttingSizePx.width
        val scaleY = (cuttingSizePx.height + cuttingSpacingPx) / cuttingSizePx.height
        val transformPath = cuttingPath.transform(scaleX, scaleY).apply {
            translate(
                Offset(x = size.width / 2, 0f)
            )
        }

        return Outline.Generic(
            path = drawCutoutPath(
                size = size,
                cuttingPath = transformPath,
            )
        )
    }

    private fun drawCutoutPath(
        size: Size,
        cuttingPath: Path,
    ): Path {
        val rectPath = Path().apply {
            addRect(
                Rect(
                    offset = Offset.Zero,
                    size = size,
                )
            )
        }

        return Path.combine(PathOperation.Difference, rectPath, cuttingPath)
    }
}