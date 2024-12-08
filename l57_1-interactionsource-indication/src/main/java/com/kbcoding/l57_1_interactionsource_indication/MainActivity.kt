package com.kbcoding.l57_1_interactionsource_indication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.createRippleModifierNode
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kbcoding.l57_1_interactionsource_indication.ui.theme.AndroidComposeBasicsTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidComposeBasicsTheme {
                CompositionLocalProvider(
                    LocalIndication provides CustomIndicationNodeFactory
                ) {
                    AppContent()
                }
            }
        }
    }
}

@Composable
fun AppContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        App()
        AppNode()
    }
}

@Composable
fun App() {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    val scaleFactor = if (isPressed.value) 1.3f else 1f
    val animatedScaleFactor by animateFloatAsState(
        targetValue = scaleFactor,
        label = "ScaleAnimation"
    )
    val indication = ripple(
        bounded = false,
        radius = 100.dp,
        color = Color.Red
    )
    val context = LocalContext.current
    Text(
        text = "Click Me",
        fontSize = 20.sp,
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = indication
            ) {
                Toast
                    .makeText(context, "Clicked", Toast.LENGTH_SHORT)
                    .show()
            }
            .padding(16.dp)
            .drawWithContent {
                scale(animatedScaleFactor) {
                    this@drawWithContent.drawContent()
                }
            }
    )
//    LaunchedEffect(Unit) {
//        launch {
//            delay(2000)
//            val press = PressInteraction.Press(Offset.Zero)
//            interactionSource.emit(press)
//            delay(2000)
//            interactionSource.emit(PressInteraction.Release(press))
//        }
//        interactionSource.interactions.collectLatest { interaction ->
//            println("AAAAA $interaction")
//        }
//    }
}

@Composable
fun AppNode() {

    val context = LocalContext.current
    Text(
        text = "Click Me 2",
        fontSize = 20.sp,
        modifier = Modifier
            .clickable {
                Toast
                    .makeText(context, "Clicked", Toast.LENGTH_SHORT)
                    .show()
            }
            .padding(16.dp)
    )
}


data object CustomIndicationNodeFactory : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return CustomIndicationNode(interactionSource)
    }
}

private class CustomIndicationNode(
    private val interactionSource: InteractionSource,
) : DelegatingNode(), DrawModifierNode {

    private val animatedScaleFactor = Animatable(1f)

    private val rippleNOde = delegate(createRippleModifierNode(
        interactionSource = interactionSource,
        bounded = false,
        radius = 100.dp,
        color = { Color.Red },
        rippleAlpha = {
            RippleAlpha(
                draggedAlpha = 0.1f,
                focusedAlpha = 0.1f,
                hoveredAlpha = 0.1f,
                pressedAlpha = 0.1f,
            )
        }
    )) as DrawModifierNode

    private var emptyContentDrawScope: EmptyContentDrawScope? = null

    override fun onAttach() {
        coroutineScope.launch {
            interactionSource.interactions.collectLatest { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> animatedScaleFactor.animateTo(1.3f)
                    else -> animatedScaleFactor.animateTo(1f)
                }
            }
        }
    }

    override fun onDetach() {
        emptyContentDrawScope = null
    }

    override fun ContentDrawScope.draw() {
        with(toEmptyContentDrawScope()) {
            with(rippleNOde) {
                draw()
            }
        }
        scale(animatedScaleFactor.value) {
            this@draw.drawContent()
        }
    }

    private fun ContentDrawScope.toEmptyContentDrawScope(): EmptyContentDrawScope {
        return emptyContentDrawScope?.takeIf {
            it.origin == this
        } ?: EmptyContentDrawScope(this).also { emptyContentDrawScope = it }
    }
}

private class EmptyContentDrawScope(
    val origin: ContentDrawScope,
) : ContentDrawScope by origin {
    override fun drawContent() = Unit
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    AndroidComposeBasicsTheme {
        AppContent()
    }
}