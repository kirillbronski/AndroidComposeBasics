package com.kbcoding.androidcomposebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var counterState by rememberSaveable {
                mutableStateOf(CounterState(Random.nextInt(1000)))
            }
            StatelessCounter(
                counterValue = counterState.count,
                onIncrement = {
                    counterState = counterState.copy(count = it)
                }
            )
        }
    }
}

@Composable
fun StatefulCounter() {

    // v1
//    val counterMutableState = remember {
//        mutableStateOf(CounterState(Random.nextInt(1000)))
//    }
    // kotlin destruction
//    val (value, setValue) = remember {
//        mutableStateOf(CounterState(Random.nextInt(1000)))
//    }
    // delegation
    var counterState by rememberSaveable {
        mutableStateOf(CounterState(Random.nextInt(1000)))
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Statefull")
        Text(
            text = counterState.count.toString(),
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            val newState = counterState.copy(count = counterState.count + 1)
            counterState = newState
        }
        ) {
            Text(text = "Increment")
        }
    }
}

@Composable
fun StatelessCounter(
    counterValue: Int = 0,
    onIncrement: (incrementedValue: Int) -> Unit = {}
) {

    // v1
//    val counterMutableState = remember {
//        mutableStateOf(CounterState(Random.nextInt(1000)))
//    }
    // kotlin destruction
//    val (value, setValue) = remember {
//        mutableStateOf(CounterState(Random.nextInt(1000)))
//    }
    // delegation
    logCompositionLifecycle(name = "StatelessCounter")
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Stateless")
        Text(
            text = counterValue.toString(),
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            onIncrement(counterValue + 1)
        }
        ) {
            Text(text = "Increment")
        }
        Box(modifier = Modifier.height(100.dp)) {
            if (counterValue % 2 == 0) {
                logCompositionLifecycle(name = "IsEventText")
                Text(text = "Is Even = true", fontSize = 18.sp)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    //StatefulCounter()
    StatelessCounter()
}