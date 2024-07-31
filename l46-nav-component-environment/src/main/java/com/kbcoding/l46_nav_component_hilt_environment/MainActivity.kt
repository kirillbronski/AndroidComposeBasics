package com.kbcoding.l46_nav_component_hilt_environment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kbcoding.l46_nav_component_environment.R
import com.kbcoding.l46_nav_component_hilt_environment.data.repository.ItemsRepository
import com.kbcoding.l46_nav_component_hilt_environment.screens.LocalNavController
import com.kbcoding.l46_nav_component_hilt_environment.screens.Route
import com.kbcoding.l46_nav_component_hilt_environment.screens.addItem.AddItemScreen
import com.kbcoding.l46_nav_component_hilt_environment.screens.items.ItemsScreen
import com.kbcoding.l46_nav_component_hilt_environment.ui.theme.AndroidComposeBasicsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: ItemsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidComposeBasicsTheme {
                    NavApp()
            }
        }
    }
}

@Composable
fun NavApp() {

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val titleRes = when (currentBackStackEntry?.destination?.route) {
        Route.Items.path -> R.string.items_screen
        Route.AddItem.path -> R.string.add_item_screen
        else -> R.string.app_name
    }

    Scaffold(
        topBar = {
            AppToolbar(
                titleRes = titleRes,
                navigateUpAction = if (navController.previousBackStackEntry == null) {
                    NavigateUpAction.Hidden
                } else {
                    NavigateUpAction.Visible(
                        onClick = { navController.navigateUp() }
                    )
                }
            )
        },
        floatingActionButton = {
            if (currentBackStackEntry?.destination?.route == Route.Items.path) {
                FloatingActionButton(
                    onClick = { navController.navigate(Route.AddItem.path) }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        }
    ) { paddingValues ->
        CompositionLocalProvider(
            value = LocalNavController provides navController
        ) {
            NavHost(
                navController = navController,
                startDestination = Route.Items.path,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                composable(Route.Items.path) { ItemsScreen() }
                composable(Route.AddItem.path) { AddItemScreen() }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NavAppPreview() {
    AndroidComposeBasicsTheme {
        NavApp()
    }
}