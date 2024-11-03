package com.kbcoding.l54_dialogs.ui.screens.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.kbcoding.l54_dialogs.ui.theme.AndroidComposeBasicsTheme

@Composable
fun SettingsScreen() {
    Text(
        text = "Settings Screen",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        textAlign = TextAlign.Center,
        fontSize = 20.sp
    )
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    AndroidComposeBasicsTheme {
        SettingsScreen()
    }
}
