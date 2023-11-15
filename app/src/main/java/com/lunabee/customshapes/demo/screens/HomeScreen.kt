package com.lunabee.customshapes.demo.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.lunabee.customshapes.demo.ui.composable.TopBar
import com.lunabee.customshapes.demo.ui.theme.spacing

const val HomeScreenRoute: String = "home"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToBasicElliptic: () -> Unit,
    navigateToPolygon: () -> Unit,
    navigateToSvg: () -> Unit,
    navigateToCutout: () -> Unit,
    navigateToGraphicsShapes: () -> Unit,
    navigateToAnimation: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(title = "Jetpack Compose Shapes")
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            homeItem(
                label = "Bottom elliptic",
                onClick = navigateToBasicElliptic,
            )
            homeItem(
                label = "Polygon",
                onClick = navigateToPolygon,
            )
            homeItem(
                label = "Svg",
                onClick = navigateToSvg,
            )
            homeItem(
                label = "Cutout",
                onClick = navigateToCutout,
            )
            homeItem(
                label = "Morphing",
                onClick = navigateToGraphicsShapes,
            )
            homeItem(
                label = "Animations",
                onClick = navigateToAnimation,
            )
        }
    }
}

private fun LazyListScope.homeItem(
    label: String,
    onClick: () -> Unit,
) {
    item(
        contentType = "homeItem",
        key = label,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(horizontal = MaterialTheme.spacing.large, vertical = MaterialTheme.spacing.small),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = label)
            Icon(imageVector = Icons.Rounded.ArrowForward, contentDescription = null)
        }
    }
}