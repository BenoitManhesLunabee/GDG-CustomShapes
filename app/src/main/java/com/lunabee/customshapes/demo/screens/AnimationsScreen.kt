package com.lunabee.customshapes.demo.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.circle
import androidx.graphics.shapes.star
import com.lunabee.customshapes.demo.shapeutils.MorphShape
import com.lunabee.customshapes.demo.shapeutils.PinShape
import com.lunabee.customshapes.demo.ui.composable.MyItem
import com.lunabee.customshapes.demo.ui.composable.TopBar
import com.lunabee.customshapes.demo.ui.res.Dimens
import com.lunabee.customshapes.demo.ui.theme.spacing

const val AnimationsScreenRoute: String = "animations"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimationsScreen(
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Animations",
                onNavigateBack = onBackPressed,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            ) {
                CornerAnimatedButton(
                    onClicked = { },
                ) {
                    Icon(painter = rememberVectorPainter(image = Icons.Rounded.Add), contentDescription = null)
                }
                MorphAnimatedButton(onClicked = {}) {
                    Icon(painter = rememberVectorPainter(image = Icons.Rounded.Add), contentDescription = null)
                }
            }

            AnimatedPin()
        }
    }
}

@Composable
fun CornerAnimatedButton(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    val radius = if (isPressed.value) {
        16.dp
    } else {
        100.dp
    }
    val cornerRadius = animateDpAsState(targetValue = radius, label = "cornerRadius")

    Surface(
        tonalElevation = 10.dp,
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius.value))
    ) {
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .size(Dimens.AnimationShape.shapeButtonSize)
                .clickable(
                    interactionSource = interactionSource,
                    indication = rememberRipple()
                ) { onClicked.invoke() },
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Composable
fun MorphAnimatedButton(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit,
    content: @Composable () -> Unit,
) {
    val morph = remember {
        Morph(
            start = RoundedPolygon.circle(),
            end = RoundedPolygon.star(
                8,
                innerRadius = .8f,
                rounding = CornerRounding(.2f),
                innerRounding = CornerRounding(.1f)
            ),
        )
    }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    val progress = if (isPressed.value) {
        1f
    } else {
        0f
    }
    val morphProgress by animateFloatAsState(targetValue = progress, label = "morphProgress")
    morph.progress = morphProgress

    Surface(
        tonalElevation = 10.dp,
        modifier = modifier
            .clip(MorphShape(morph))
    ) {
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .size(Dimens.AnimationShape.shapeButtonSize)
                .clickable(
                    interactionSource = interactionSource,
                    indication = rememberRipple()
                ) { onClicked.invoke() },
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Composable
fun AnimatedPin() {
    var showTip by remember { mutableStateOf(true) }
    val progress by animateFloatAsState(
        targetValue = if (showTip) 1f else 0f,
        label = "tip height progress",
    )

    MyItem(
        shape = PinShape(tipHeightProgress = progress),
        size = Dimens.AnimationShape.pinSize,
        onClick = {
            showTip = !showTip
        }
    )
}