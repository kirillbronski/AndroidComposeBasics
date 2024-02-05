package com.kbcoding.androidcomposebasics.simple

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.kbcoding.androidcomposebasics.simple.ui.theme.AndroidComposeBasicsTheme

class SimpleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ButtonSimpleExample()
        }
    }
}

@Composable
private fun ButtonSimpleExample() {
    val context: Context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = {
                Toast.makeText(
                    context,
                    "Hello World",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ) {
            Text("Click Me")
        }
    }
}

@Composable
fun CheckboxSimpleExample() {
    var state: Boolean by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Checkbox(
            checked = state,
            onCheckedChange = { newCheckState ->
                state = newCheckState
            }
        )
    }
}

@Composable
private fun TextFieldSimpleExample() {
    var textValue: String by remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        OutlinedTextField(
            value = textValue,
            onValueChange = {
                textValue = it
            }
        )
    }
}