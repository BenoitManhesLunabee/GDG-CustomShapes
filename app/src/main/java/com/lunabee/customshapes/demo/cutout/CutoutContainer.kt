package com.lunabee.customshapes.demo.cutout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

@Composable
fun CutoutContainer(
    content: @Composable CutoutScope.() -> Unit,
    container: @Composable () -> Unit,
) {

}

@Composable
private fun CutoutLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
//    Layout()
}