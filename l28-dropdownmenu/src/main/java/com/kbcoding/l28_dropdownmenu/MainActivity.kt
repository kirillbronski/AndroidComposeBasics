package com.kbcoding.l28_dropdownmenu

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun AppScreen() {
    var showPopupMenu by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(R.string.popup_menu_example))
        Spacer(modifier = Modifier.height(12.dp))
        Box {
            Button(
                onClick = {
                    showPopupMenu = true
                },
            ) {
                Text(text = stringResource(R.string.show))
            }
            DropdownMenu(
                expanded = showPopupMenu,
                onDismissRequest = { showPopupMenu = false },
                offset = DpOffset(x = 0.dp, y = 0.dp),
                properties = PopupProperties(),
            ) {
                repeat(3) { index ->
                    val itemPosition = index + 1
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.menu_item, itemPosition)) },
                        onClick = {
                            Toast.makeText(
                                context,
                                context.getString(R.string.clicked_on_item, itemPosition),
                                Toast.LENGTH_SHORT,
                            ).show()
                            showPopupMenu = false
                        },
                    )
                }
            }
        }
    }
}