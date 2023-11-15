package com.lunabee.customshapes.demo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.lunabee.customshapes.R
import com.lunabee.customshapes.demo.ui.composable.MyItem
import com.lunabee.customshapes.demo.ui.composable.TopBar
import com.lunabee.customshapes.demo.ui.res.Dimens
import com.lunabee.customshapes.demo.ui.res.shapes.LunabeeShape
import com.lunabee.customshapes.demo.ui.res.shapes.LyonSkylineShape
import com.lunabee.customshapes.demo.ui.theme.spacing

const val SvgScreenRoute: String = "svg"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SvgScreen(
    onBackPressed: () -> Unit,
) {
    var openDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar(
                title = "Shape from svg",
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lunabee),
                    contentDescription = null,
                    modifier = Modifier.size(Dimens.Svg.imageSize),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
                Spacer(modifier = Modifier.size(MaterialTheme.spacing.small))
                Text("Original svg")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row {
                    MyItem(
                        shape = LunabeeShape,
                        size = Dimens.Svg.imageSize,
                    )
                    Spacer(modifier = Modifier.size(MaterialTheme.spacing.large))
                    Image(
                        painter = painterResource(id = R.drawable.pelvoux),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(Dimens.Svg.imageSize)
                            .clip(LunabeeShape),
                    )
                }
                Spacer(modifier = Modifier.size(MaterialTheme.spacing.small))
                Text("Shape from svg")
            }

            Button(
                onClick = { openDialog = true },
            ) {
                Text("show svg dialog")
            }
        }
    }

    if (openDialog) {
        SkylineAlertDialog(
            onDismiss = { openDialog = false },
            content = {
                Text(
                    text = "svg dialog",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
        )
    }
}

@Composable
private fun SkylineAlertDialog(
    onDismiss: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Column {
            Surface(
                shape = LyonSkylineShape,
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 50.dp),
            ) {}
            Surface(
                shape = RoundedCornerShape(bottomStart = Dimens.Corner.small, bottomEnd = Dimens.Corner.small),
                color = MaterialTheme.colorScheme.surface,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 300.dp)
                        .padding(MaterialTheme.spacing.medium),
                ) {
                    content()
                }
            }
        }
    }
}