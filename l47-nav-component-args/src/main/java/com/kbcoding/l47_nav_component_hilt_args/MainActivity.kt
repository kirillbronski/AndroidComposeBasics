package com.kbcoding.l47_nav_component_hilt_args

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import com.kbcoding.l47_nav_component_args.R
import com.kbcoding.l47_nav_component_hilt_args.data.repository.ItemsRepository
import com.kbcoding.l47_nav_component_hilt_args.navigation.ScreenEnvironment
import com.kbcoding.l47_nav_component_hilt_args.navigation.rememberNavigation
import com.kbcoding.l47_nav_component_hilt_args.screens.LocalNavController
import com.kbcoding.l47_nav_component_hilt_args.screens.Route
import com.kbcoding.l47_nav_component_hilt_args.screens.addItem.AddItemScreen
import com.kbcoding.l47_nav_component_hilt_args.screens.items.itemsScreen
import com.kbcoding.l47_nav_component_hilt_args.ui.theme.AndroidComposeBasicsTheme
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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavApp()
                }
            }
        }
    }
}

@Composable
fun NavApp() {
    val navigation = rememberNavigation(startDestination = Route.Items.path) {
        screen(Route.Items.path) { itemsScreen() }
        screen(Route.AddItem.path) {
            content { AddItemScreen() }
            environment {
                titleRes = R.string.add_item_screen
            }
        }
    }
    val navController = navigation.navController
    val navGraph = navigation.navGraph
    val environment: ScreenEnvironment = navigation.environment

    Scaffold(
        topBar = {
            AppToolbar(
                navigateUpAction = if (navController.previousBackStackEntry == null) {
                    NavigateUpAction.Hidden
                } else {
                    NavigateUpAction.Visible(
                        onClick = { navController.navigateUp() }
                    )
                },
                titleRes = environment.titleRes,
            )
        },
        floatingActionButton = {
            val floatingAction = environment.floatingAction
            if (floatingAction != null) {
                FloatingActionButton(
                    onClick = floatingAction.onClick
                ) {
                    Icon(imageVector = floatingAction.icon, contentDescription = null)
                }
            }
        }
    ) {
        CompositionLocalProvider(
            LocalNavController provides navController,
        ) {
            NavHost(
                navController = navController,
                graph = navGraph,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            )
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