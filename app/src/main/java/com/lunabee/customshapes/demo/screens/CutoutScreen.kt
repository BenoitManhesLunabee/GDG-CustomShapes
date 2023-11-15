package com.lunabee.customshapes.demo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lunabee.customshapes.demo.cutout.CutoutShape
import com.lunabee.customshapes.demo.shapeutils.PolygonShape
import com.lunabee.customshapes.demo.ui.composable.TopBar
import com.lunabee.customshapes.demo.ui.res.Gradients
import com.lunabee.customshapes.demo.ui.theme.spacing
import com.lunabee.customshapes.demo.utils.polygon

const val CutoutScreenRoute: String = "cutout"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutoutScreen(
    onBackPressed: () -> Unit,
) {
    var polygonSides by remember {
        mutableStateOf(minSides)
    }
    var cuttingSpacing by remember {
        mutableStateOf(minCutoutSpacing)
    }

    val cuttingSpacingDp = with(LocalDensity.current) {
        cuttingSpacing.toDp()
    }

    val (cutoutShape, buttonShape) = remember(polygonSides, cuttingSpacingDp) {
        CutoutShape(
            cuttingPath = Path().apply { polygon(polygonSides, 100f, Offset.Zero) },
            cuttingSpacing = cuttingSpacingDp,
        ) to PolygonShape(polygonSides)
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Cutout shapes",
                onNavigateBack = onBackPressed,
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.huge)
                    .align(Alignment.Center),
            ) {
                Text(text = "Polygon sides")
                Slider(
                    value = polygonSides.toFloat(),
                    onValueChange = {
                        polygonSides = it.toInt()
                    },
                    valueRange = minSides.toFloat()..maxSides.toFloat(),
                    steps = maxSides - minSides,
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

                Text(text = "Cutting Spacing")
                Slider(
                    value = cuttingSpacing,
                    onValueChange = {
                        cuttingSpacing = it
                    },
                    valueRange = minCutoutSpacing..maxCutoutSpacing,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BottomBarHeight)
                    .align(Alignment.BottomCenter)
                    .clip(cutoutShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable { },
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = BottomBarHeight / 2)
                    .size(ButtonSize)
                    .clip(buttonShape)
                    .background(Gradients.flashyGradient)
            ) {}
        }
    }
}

private const val minSides: Int = 3
private const val maxSides: Int = 12
private const val minCutoutSpacing: Float = 10f
private const val maxCutoutSpacing: Float = 100f

private val ButtonSize: Dp = 70.dp
private val BottomBarHeight: Dp = 70.dp