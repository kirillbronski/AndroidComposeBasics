package com.kbcoding.androidcomposebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.kbcoding.androidcomposebasics.base.Container

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        VectorIcon()
        MaterialIcon()
        TintedVectorIcon()
        IconWithModifier()
        SimpleImage()
        ContentScaleCropImage()
        SquareImage()
        ClippedImage()
        ClippedWithCustomShapeImage()
        SimpleAsyncLoadedImage()
        AsyncLoadedImage()

        Spacer(modifier = Modifier.height(12.dp))
    }
}

// ---

@Composable
fun VectorIcon() {
    Container(name = "Simple vector icon") {
        Icon(
            painter = painterResource(R.drawable.umbrella),
            contentDescription = "Umbrella icon"
        )
    }
}

@Composable
fun MaterialIcon() {
    Container(name = stringResource(R.string.material_icon)) {
        Icon(
            imageVector = Icons.Rounded.MailOutline,
            contentDescription = stringResource(R.string.material_mail_icon),
            modifier = Modifier.size(48.dp)
        )
    }
}

// ---

@Composable
fun TintedVectorIcon() {
    Container(name = stringResource(R.string.tinted_vector_icon)) {
        Icon(
            painter = painterResource(R.drawable.umbrella),
            contentDescription = stringResource(R.string.umbrella_icon),
            tint = Color.Red,
        )
    }
}

// ---

@Composable
fun IconWithModifier() {
    Container(name = stringResource(R.string.vector_icon_with_modifiers)) {
        Icon(
            painter = painterResource(R.drawable.umbrella),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .background(Color.LightGray, CircleShape)
                .padding(8.dp)
        )
    }
}

// ---

@Composable
fun SimpleImage() {
    Container(name = stringResource(R.string.simple_image)) {
        Image(
            painter = painterResource(R.drawable.img1),
            contentDescription = stringResource(R.string.random_architecture_showcase)
        )
    }
}

// ---

@Composable
fun ContentScaleCropImage() {
    Container(name = stringResource(R.string.image_with_contentscale)) {
        Image(
            painter = painterResource(R.drawable.img2),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.random_architecture_showcase),
        )
    }
}

// ---

@Composable
fun SquareImage() {
    Container(name = stringResource(R.string.image_cropped_by_aspect_ratio)) {
        Image(
            painter = painterResource(R.drawable.img3),
            contentDescription = stringResource(R.string.random_architecture_showcase),
            contentScale = ContentScale.Crop,
            modifier = Modifier.aspectRatio(1f / 1f)
        )
    }
}

// ---

@Composable
fun ClippedImage() {
    Container(name = stringResource(R.string.clipped_image)) {
        Image(
            painter = painterResource(R.drawable.img4),
            contentDescription = stringResource(R.string.random_architecture_showcase),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f / 1f)
                .clip(CircleShape)
        )
    }
}

// ---

@Composable
fun ClippedWithCustomShapeImage() {
    Container(name = stringResource(R.string.clipped_image_with_custom_shape)) {
        Image(
            painter = painterResource(R.drawable.img5),
            contentDescription = stringResource(R.string.random_architecture_showcase),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f / 1f)
                .clip(GenericShape { size, _ ->
                    moveTo(0f, size.height)
                    lineTo(size.width / 2, 0f)
                    lineTo(size.width, size.height)
                })
        )
    }
}

// ---

@Composable
fun SimpleAsyncLoadedImage() {
    Container(name = stringResource(R.string.simple_async_load)) {
        AsyncImage(
            model = "https://images.unsplash.com/photo-1441974231531-c6227db76b6e?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218MHx8bmF0dXJlfHx8fHx8MTY5NDkzNTM5Ng&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            contentDescription = null,
        )
    }
}

// ---

enum class RequestState {
    LOAD_NOT_REQUESTED,
    LOAD_REQUESTED,
}

@Composable
fun AsyncLoadedImage() {
    var state by remember {
        mutableStateOf(RequestState.LOAD_NOT_REQUESTED)
    }

    Container(name = stringResource(R.string.async_load_by_coil)) {
        when (state) {
            RequestState.LOAD_NOT_REQUESTED -> LoadButton { state = RequestState.LOAD_REQUESTED }
            RequestState.LOAD_REQUESTED -> {
                SubcomposeAsyncImage(
                    model = "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218MHx8bmF0dXJlfHx8fHx8MTY5NDkzNTA0MQ&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
                    contentDescription = null,
                    loading = {
                        CircularProgressIndicator()
                    },
                    error = {
                        Text(stringResource(R.string.load_failed), color = Color.Red)
                    },
                )
            }
        }
    }
}

@Composable
private fun LoadButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(stringResource(R.string.load))
    }
}
