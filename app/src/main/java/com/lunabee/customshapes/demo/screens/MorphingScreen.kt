package com.lunabee.customshapes.demo.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import com.lunabee.customshapes.R
import com.lunabee.customshapes.demo.shapeutils.MorphShape
import com.lunabee.customshapes.demo.shapeutils.RoundedPolygonShape
import com.lunabee.customshapes.demo.ui.composable.MyItem
import com.lunabee.customshapes.demo.ui.composable.TopBar
import com.lunabee.customshapes.demo.ui.res.Gradients
import com.lunabee.customshapes.demo.ui.theme.spacing
import com.lunabee.customshapes.demo.utils.RoundedPolygonUtils

const val GraphicsShapesScreenRoute: String = "morphing"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MorphingScreen(
    onBackPressed: () -> Unit,
) {
    var currentShape by remember {
        mutableStateOf(shapes[0])
    }
    val morphProgress = remember { Animatable(0f) }
    var morph by remember {
        mutableStateOf(Morph(shapes[0], shapes[0]))
    }
    morph.progress = morphProgress.value

    LaunchedEffect(morph) {
        morphProgress.animateTo(
            if (morphProgress.value == 1f) 0f else 1f,
            animationSpec = tween(300),
        )
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Morphing",
                onNavigateBack = onBackPressed,
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(5),
                    contentPadding = PaddingValues(MaterialTheme.spacing.large),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                ) {
                    this.items(items = shapes) { roundPolygon ->
                        MyItem(
                            shape = RoundedPolygonShape(roundPolygon),
                        ) {
                            if (morphProgress.value == 1f) {
                                morph = Morph(
                                    start = roundPolygon,
                                    end = currentShape
                                )
                            } else {
                                morph = Morph(
                                    start = currentShape,
                                    end = roundPolygon,
                                )
                            }

                            currentShape = roundPolygon
                        }
                    }
                }
                Image(
                    painterResource(id = R.drawable.pelvoux),
                    contentDescription = null,
                    modifier = Modifier
                        .size(300.dp)
                        .clip(MorphShape(morph)),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}


private val shapes: List<RoundedPolygon> = listOf(
    // Row 1: Unrounded
    RoundedPolygon(3),
    RoundedPolygon.star(5),
    RoundedPolygonUtils.triangle(cornerRounding = CornerRounding(.1f)),
    RoundedPolygonUtils.blob(innerRadius = .19f, roundness = 0.86f),
    RoundedPolygonUtils.cornerSE(),

    // Row 2: Rounded
    RoundedPolygon(6, rounding = CornerRounding(.2f)),
    RoundedPolygon.star(6, rounding = CornerRounding(.2f), innerRounding = CornerRounding(.2f)),
    RoundedPolygon.star(12, innerRadius = .9f, rounding = CornerRounding(.1f), innerRounding = CornerRounding(.1f)),
)
