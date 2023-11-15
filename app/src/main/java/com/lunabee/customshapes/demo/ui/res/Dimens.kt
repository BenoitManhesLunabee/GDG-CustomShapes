package com.lunabee.customshapes.demo.ui.res

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

@Immutable
object Dimens {
    object Spacing {
        val small: Dp = 8.dp
        val medium: Dp = 12.dp
        val large: Dp = 16.dp
        val huge: Dp = 32.dp
    }

    object Corner {
        val small: Dp = 8.dp
    }

    object PolygonScreen {
        val imageSize: DpSize = DpSize(200.dp, 200.dp)
    }

    object AnimationShape {
        val shapeButtonSize: Dp = 64.dp
        val pinSize: DpSize = DpSize(128.dp, 64.dp)
    }

    object BottomEllipticScreen {
        val bottomBoxHeight: Dp = 200.dp
    }

    object Svg {
        val imageSize: DpSize = DpSize(150.dp, 150.dp)
    }
}