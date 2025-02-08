package com.kbcoding.l65_canvas_graphics_layer

import android.graphics.RuntimeShader
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun Playground(modifier: Modifier = Modifier) {
    val shader = RuntimeShader(SHADER_PROGRAM)
    val brush = ShaderBrush(shader)
    val controller = rememberEasyCanvasController(
        initialContentRect = Rect(
            center = Offset.Zero,
            radius = 2.2f,
        ),
        initialContentScale = CanvasContentScale.CenterInside,
    )
    EasyCanvas(
        controller = controller,
        modifier = modifier.sizeIn(200.dp, 200.dp),
        useGraphicLayer = true,
    ) {
        onDrawBehind {
            println("AAA onDraw")
            //withContentTransform {
            drawRect(
                brush = brush,
                topLeft = contentRect.topLeft,
                size = contentRect.size,
            )
            //}
        }
    }
}