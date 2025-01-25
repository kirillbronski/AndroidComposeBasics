package com.kbcoding.l_59_canvas_linear_transform

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun Playground(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()
    val animateGradient = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "GradientAnimation"
    )
    Box(modifier = modifier
        .sizeIn(300.dp, 200.dp)
        .drawBehind {
            val contentRect = Rect(
                offset = Offset(200f, 100f),
                size = Size(size.minDimension / 1.5f, size.minDimension / 1.5f)
            )

            val cellSize = contentRect.size / 8f
            val gradientStart = contentRect.size.width * animateGradient.value
            val gradientEnd = contentRect.size.width * (animateGradient.value + 0.5f)
            val brush = Brush.linearGradient(
                start = Offset(gradientStart, gradientStart),
                end = Offset(gradientEnd, gradientEnd),
                tileMode = TileMode.Mirror,
                colors = listOf(
                    Color.Blue,
                    Color.Black,
                    Color.Red,
                    Color.Green
                ),
            )
            for (i in 0..7) {
                for (j in 0..7) {
                    val topLeft = contentRect.topLeft + Offset(x = i * cellSize.width, y = j * cellSize.height)
                    if ((i + j) % 2 == 0) {
                        drawRect(
                            brush = brush,
                            //color = Color.Black,
                            topLeft = topLeft,
                            size = cellSize
                        )
                    } else {
                        drawRect(
                            color = Color.White,
                            topLeft = topLeft,
                            size = cellSize
                        )
                    }
                }
            }
            drawRect(
                color = Color.Black,
                topLeft = contentRect.topLeft,
                size = contentRect.size,
                style = Stroke(width = 3.dp.toPx())
            )
        })
}