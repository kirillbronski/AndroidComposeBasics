package com.kbcoding.l43_nav_component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kbcoding.l43_nav_component.screens.LocalNavController
import com.kbcoding.l43_nav_component.screens.Route
import com.kbcoding.l43_nav_component.screens.addItem.AddItemScreen
import com.kbcoding.l43_nav_component.screens.items.ItemsScreen
import com.kbcoding.l43_nav_component.ui.theme.AndroidComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidComposeBasicsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NavApp(
    modifier: Modifier
) {

    val navController = rememberNavController()
    CompositionLocalProvider(
        value = LocalNavController provides navController
    ) {
        NavHost(
            navController = navController,
            startDestination = Route.Items.path,
            modifier = modifier.fillMaxSize()
        ) {
            composable(Route.Items.path) { ItemsScreen() }
            composable(Route.AddItem.path) { AddItemScreen() }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NavAppPreview() {
    AndroidComposeBasicsTheme {
        NavApp(modifier = Modifier)
    }
}