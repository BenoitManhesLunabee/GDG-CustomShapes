package com.lunabee.customshapes.demo.ui.res.shapes

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Shape
import com.lunabee.customshapes.demo.shapeutils.SvgShape

val LunabeeShape: Shape = SvgShape(
    viewportSize = Size(594f, 594f),
    pathData = """m242.4 245.2c-53.3 81-102.8 156.3-110.1 167.3l-13.3 20c170.9 0.4 270.3 0.4 331.2 0.3l110.6-0.3c-170.3-258.9-220.1-334.1-220.6-334.3-0.5-0.2-44.5 66-97.8 147zm-142.1 19.5l-75.2 113.8c64.6 0.6 67.7 0.4 70.8-1.2 2.7-1.4 8.7-9.8 28.1-38.8 13.5-20.4 37.5-56.6 53.3-80.5 15.7-23.9 29.3-45.2 30.1-47.2 0.9-2.1 1.6-4.9 1.6-6.3 0-1.4-2.3-6.2-5.2-10.7-2.8-4.6-10.1-15.7-16.1-24.8-6-9.1-11.3-16.8-11.6-17.3-0.4-0.4-34.5 50.5-75.8 113z"""
)