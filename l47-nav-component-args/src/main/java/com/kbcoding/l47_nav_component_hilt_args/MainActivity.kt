package com.kbcoding.l47_nav_component_hilt_args

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kbcoding.l47_nav_component_args.R
import com.kbcoding.l47_nav_component_hilt_args.screens.LocalNavController
import com.kbcoding.l47_nav_component_hilt_args.screens.RouteAddItem
import com.kbcoding.l47_nav_component_hilt_args.screens.RouteEditItem
import com.kbcoding.l47_nav_component_hilt_args.screens.RouteItems
import com.kbcoding.l47_nav_component_hilt_args.screens.addItem.AddItemScreen
import com.kbcoding.l47_nav_component_hilt_args.screens.edit.EditItemScreen
import com.kbcoding.l47_nav_component_hilt_args.screens.items.ItemsScreen
import com.kbcoding.l47_nav_component_hilt_args.screens.routeClass
import com.kbcoding.l47_nav_component_hilt_args.ui.theme.AndroidComposeBasicsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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
    val titleRes = when (currentBackStackEntry.routeClass()) {
        RouteItems::class -> R.string.items_screen
        RouteAddItem::class -> R.string.add_item_screen
        RouteEditItem::class -> R.string.add_item_screen
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
            if (currentBackStackEntry?.routeClass() == RouteItems::class) {
                FloatingActionButton(
                    onClick = { navController.navigate(RouteAddItem) }
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
                startDestination = RouteItems,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                composable<RouteItems> { ItemsScreen() }
                composable<RouteAddItem> { AddItemScreen() }
                composable<RouteEditItem> { navBackStackEntry ->
                    val route: RouteEditItem = navBackStackEntry.toRoute()
                    EditItemScreen(index = route.index)
                }
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