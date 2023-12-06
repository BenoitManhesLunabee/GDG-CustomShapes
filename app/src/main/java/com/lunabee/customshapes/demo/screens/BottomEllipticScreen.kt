package com.lunabee.customshapes.demo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.lunabee.customshapes.demo.shapeutils.BottomEllipticShape
import com.lunabee.customshapes.demo.ui.composable.TopBar
import com.lunabee.customshapes.demo.ui.res.Dimens
import com.lunabee.customshapes.demo.ui.res.Gradients
import com.lunabee.customshapes.demo.ui.theme.spacing

const val BottomEllipticScreenRoute: String = "bottom-elliptic"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomEllipticScreen(
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = { TopBar(title = "Bottom Elliptic", onNavigateBack = onBackPressed) },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(BottomEllipticShape(1.24f, 1.08f))
                    .background(Gradients.flashyGradient),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.BottomEllipticScreen.bottomBoxHeight)
            ) {
                Text(
                    text = "Bottom elliptic shape",
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.large)
                        .align(Alignment.Center),
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}