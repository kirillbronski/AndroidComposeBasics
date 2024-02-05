package com.kbcoding.androidcomposebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StatefulCounter()
        }
    }
}

@Composable
fun StatefulCounter() {

    // v1
    var counterState by remember {
        mutableStateOf(0)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = counterState.toString(),
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            counterState += 1
        }
        ) {
            Text(text = "Increment", fontSize = 18.sp)
        }
        val offsetValue by remember {
            derivedStateOf {
                counterState / 3
            }
        }
        Text(
            text = "Test",
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .size(width = 60.dp, height = 60.dp)
                //.offset(y = 20.dp * (counterMutableState.value / 3))
//                .offset {
//                    IntOffset(y = 20 * (counterState / 3), x = 0)
//                }
                .offset {
                    IntOffset(y = 20 * offsetValue, x = 0)
                }
                .background(Color.Blue)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    StatefulCounter()
}