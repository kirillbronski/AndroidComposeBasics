package com.kbcoding.l21_sideeffects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlin.concurrent.timer
import kotlin.random.Random

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
    var counter by rememberSaveable {
        mutableStateOf(0)
    }
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {
        val (
            counterTextRef,
            incrementButtonRef,
        ) = createRefs()

        // example of SideEffect
        SideEffect {
            println("SideEffect, counter = $counter")
        }

        Text(
            text = counter.toString(),
            fontSize = 32.sp,
            modifier = Modifier.constrainAs(counterTextRef) {
                centerHorizontallyTo(parent)
            }
        )

        Button(
            onClick = {
                counter++
            },
            modifier = Modifier.constrainAs(incrementButtonRef) {
                centerHorizontallyTo(parent)
            }
        ) {
            Text(text = stringResource(R.string.increment))
        }

        createVerticalChain(
            counterTextRef, incrementButtonRef,
            chainStyle = ChainStyle.Packed,
        )

        if (counter % 4 < 2) {

            // example of LaunchedEffect
            LaunchedEffect(0) {
                println("Launched SideEffect - started")
                try {
                    while (true) {
                        println("Launched SideEffect - running ${Random.nextInt(1000)}")
                        delay(1000)
                    }
                } catch (e: CancellationException) {
                    println("Launched SideEffect - cancelled")
                    throw e
                }
            }

            // example of DisposableEffect
            DisposableEffect(0) {
                println("Disposable SideEffect - started")
                val timer = timer(period = 1000L) {
                    println("Disposable SideEffect - running ${Random.nextInt(1000)}")
                }

                onDispose {
                    timer.cancel()
                    println("Disposable SideEffect - cancelled")
                }
            }

            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.Red)
                    .constrainAs(createRef()) {
                        top.linkTo(incrementButtonRef.bottom, margin = 6.dp)
                        centerHorizontallyTo(parent)
                    }
            )
        }

    }
}