package com.kbcoding.animation_scrossfade_animatedcontent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kbcoding.animation_scrossfade_animatedcontent.ui.theme.AndroidComposeBasicsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class Scene {
    Button, Loading, Image,
}

private val imageIDs = listOf(
    R.drawable.img1,
    R.drawable.img2,
    R.drawable.img3,
    R.drawable.img4,
    R.drawable.img5,
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidComposeBasicsTheme {
                AnimatedContentApp()
                //CrossFadeApp()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CrossFadeApp() {
    var currentScene by remember { mutableStateOf(Scene.Button) }
    val scope = rememberCoroutineScope()
    Crossfade(
        targetState = currentScene,
        label = "SwitchScene",
        animationSpec = tween(1000)
    ) { crossfadeScene ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (crossfadeScene) {
                Scene.Button -> ButtonScene {
                    scope.launch {
                        currentScene = Scene.Loading
                        delay(2000)
                        currentScene = Scene.Image
                    }
                }
                Scene.Loading -> LoadingScene()
                Scene.Image -> ImageScene(
                    onReset = {
                        currentScene = Scene.Button
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AnimatedContentApp() {
    var currentScene by remember { mutableStateOf(Scene.Button) }
    val scope = rememberCoroutineScope()
    AnimatedContent(
        targetState = currentScene,
        label = "SwitchScene",
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(1000)
            ) togetherWith slideOutHorizontally(
                targetOffsetX =  { fullWidth -> -fullWidth },
                animationSpec = tween(1000)
            )
        }
    ) { crossfadeScene ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (crossfadeScene) {
                Scene.Button -> ButtonScene {
                    scope.launch {
                        currentScene = Scene.Loading
                        delay(2000)
                        currentScene = Scene.Image
                    }
                }
                Scene.Loading -> LoadingScene()
                Scene.Image -> ImageScene(
                    onReset = {
                        currentScene = Scene.Button
                    }
                )
            }
        }
    }
}

@Composable
fun ButtonScene(
    onStartLoading: () -> Unit,
) {
    Button(
        onClick = onStartLoading,
    ) {
        Text(text = "Load Random Image")
    }
}

@Composable
fun LoadingScene() {
    CircularProgressIndicator()
}

@Composable
fun ImageScene(
    onReset: () -> Unit,
) {
    val randomImageId = remember { imageIDs.random() }
    Image(
        painter = painterResource(randomImageId),
        contentDescription = null,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onReset() }
    )
}