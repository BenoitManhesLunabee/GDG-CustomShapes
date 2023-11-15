package com.lunabee.customshapes.demo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.lunabee.customshapes.demo.ui.res.Dimens

private val LocalShape: ProvidableCompositionLocal<Shape> = staticCompositionLocalOf { Shape }
private val LocalSpacing: ProvidableCompositionLocal<Dimens.Spacing> = staticCompositionLocalOf { Dimens.Spacing }

@Composable
fun CSTheme(
    content: @Composable () -> Unit,
) {

    CompositionLocalProvider(
        LocalShape provides Shape,
        LocalSpacing provides Dimens.Spacing,
    ) {
        MaterialTheme(
            colorScheme = ColorScheme,
            typography = Typography,
            content = content
        )
    }
}

val MaterialTheme.shape: Shape
    @Composable
    get() = LocalShape.current

val MaterialTheme.spacing: Dimens.Spacing
    @Composable
    get() = LocalSpacing.current
