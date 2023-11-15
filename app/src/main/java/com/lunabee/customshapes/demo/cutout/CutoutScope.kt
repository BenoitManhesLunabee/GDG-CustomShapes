package com.lunabee.customshapes.demo.cutout

import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection

class CutoutScope {

    @Stable
    fun Modifier.anchor(alignment: Alignment): Modifier = this.composed {
        var intSize by remember { mutableStateOf(IntSize.Zero) }
        onSizeChanged { intSize = it }.offset {
            alignment.align(intSize, IntSize.Zero, LayoutDirection.Ltr)
        }
    }
}