package com.lunabee.customshapes.demo.ui.res

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush

@Immutable
object Gradients {
    val flashyGradient: Brush = Brush.linearGradient(
        colors = listOf(ElectricIndigo, FashionFuchsia)
    )
}