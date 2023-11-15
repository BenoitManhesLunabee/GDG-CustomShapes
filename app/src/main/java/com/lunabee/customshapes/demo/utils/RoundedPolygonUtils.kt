package com.lunabee.customshapes.demo.utils

import android.graphics.PointF
import androidx.annotation.FloatRange
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon

object RoundedPolygonUtils {

    fun triangle(
        @FloatRange(from = 0.0) innerRadius: Float = 0f,
        cornerRounding: CornerRounding = CornerRounding(),
    ): RoundedPolygon {
        val points = listOf(
            radialToCartesian(1f, 270f.toRadians()),
            radialToCartesian(1f, 30f.toRadians()),
            radialToCartesian(innerRadius, 90f.toRadians()),
            radialToCartesian(1f, 150f.toRadians()),
        )
        return RoundedPolygon(
            vertices = points,
            cornerRounding,
            center = PointZero,
        )
    }

    fun cornerSE(
        cornerRounding: CornerRounding = CornerRounding(),
    ): RoundedPolygon =
        RoundedPolygon(
            vertices = SquarePoints(),
            perVertexRounding = listOf(
                cornerRounding,
                CornerRounding(1f),
                CornerRounding(1f),
                CornerRounding(1f)
            ),
            center = PointZero
        )

    fun blob(
        @FloatRange(from = 0.1) innerRadius: Float = .1f,
        @FloatRange(from = 0.1) roundness: Float = .1f,
        @FloatRange(from = 0.0, to = 1.0) smoothing: Float = 0f,
    ): RoundedPolygon {
        val sx = innerRadius.coerceAtLeast(0.1f)
        val sy = roundness.coerceAtLeast(0.1f)
        return RoundedPolygon(
            listOf(
                PointF(-sx, -sy),
                PointF(sx, -sy),
                PointF(sx, sy),
                PointF(-sx, sy),
            ),
            rounding = CornerRounding(roundness, smoothing),
            center = PointZero
        )
    }
}