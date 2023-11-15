package com.lunabee.customshapes.demo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.lunabee.customshapes.demo.shapeutils.PolygonShape
import com.lunabee.customshapes.demo.ui.composable.TopBar
import com.lunabee.customshapes.demo.ui.res.Dimens
import com.lunabee.customshapes.demo.ui.res.Gradients
import com.lunabee.customshapes.demo.ui.theme.spacing

const val PolygonScreenRoute: String = "polygon"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PolygonScreen(
    onBackPressed: () -> Unit,
) {
    var polygonSides by remember {
        mutableStateOf(minSides)
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Polygon shapes",
                onNavigateBack = onBackPressed,
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .size(Dimens.PolygonScreen.imageSize)
                    .align(Alignment.Center)
                    .clip(PolygonShape(polygonSides))
                    .background(Gradients.flashyGradient)
                    .clickable { },
            )

            Slider(
                value = polygonSides.toFloat(),
                onValueChange = {
                    polygonSides = it.toInt()
                },
                valueRange = minSides.toFloat()..maxSides.toFloat(),
                steps = maxSides - minSides,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.huge)
                    .align(Alignment.BottomCenter),
            )
        }
    }
}

private const val minSides: Int = 3
private const val maxSides: Int = 12
