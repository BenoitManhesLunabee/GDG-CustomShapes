package com.lunabee.customshapes.demo.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.lunabee.customshapes.demo.ui.res.Gradients

/**
 * A simple composable to illustrate shape use.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyItem(
    shape: Shape,
    modifier: Modifier = Modifier,
    size: DpSize = DpSize(100.dp, 100.dp),
    onClick: () -> Unit = {},
) {
    Surface(
        modifier = modifier,
        shape = shape,
        tonalElevation = 8.dp,
        shadowElevation = 4.dp,
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .background(Gradients.flashyGradient)
        )
    }
}