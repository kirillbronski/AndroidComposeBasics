package com.kbcoding.l22_sideeffects

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
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
import kotlinx.coroutines.launch

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

    // example of derivedStateOf:
    val shouldShowSquare by remember {
        derivedStateOf {
            println("derivedStateOf")
            counter % 6 > 2
        }
    }

    // example of snapshotFlow:
    LaunchedEffect(Unit) {
        snapshotFlow { counter }
            .collect {
                println("snapshotFlow, collected: $it")
            }
    }

    // example of rememberUpdatedState:
    AnotherOneFunction(counter)

    // example of rememberCoroutineScope:
    val appScreenScope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {
        println("composition start")
        val (
            counterTextRef,
            incrementButtonRef,
        ) = createRefs()

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
                appScreenScope.launch {
                    try {
                        println("rememberCoroutineScope - started")
                        delay(5000)
                        println("rememberCoroutineScope - finished")
                    } catch (e: CancellationException) {
                        println("rememberCoroutineScope - cancelled")
                        throw e
                    }
                }
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

        if (shouldShowSquare) {
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
        println("composition stop")
    }
}

@Composable
fun AnotherOneFunction(value: Int) {
    val valueState by rememberUpdatedState(value)
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            println("rememberUpdatedState - counterFromState=$valueState, counterFromArg=$value")
        }
    }
}