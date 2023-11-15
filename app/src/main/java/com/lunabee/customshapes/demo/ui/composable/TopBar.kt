package com.lunabee.customshapes.demo.ui.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    onNavigateBack: (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Text(title)
        },
        navigationIcon = {
            onNavigateBack?.let {
                IconButton(onClick = onNavigateBack) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, null)
                }
            }
        },
    )
}