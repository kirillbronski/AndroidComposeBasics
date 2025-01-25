package com.kbcoding.l41_nav_view_models

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kbcoding.l41_nav_view_models.ui.AppScaffold
import com.kbcoding.l41_nav_view_models.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                AppScaffold()
            }
        }
    }
}


@Composable
@Preview(showSystemUi = true)
fun AppScreenPreview() {
    AppTheme(useDarkTheme = false) {
        AppScaffold()
    }
}