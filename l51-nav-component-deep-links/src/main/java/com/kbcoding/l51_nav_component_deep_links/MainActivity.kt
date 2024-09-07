package com.kbcoding.l51_nav_component_deep_links

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
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kbcoding.l51_nav_component_deep_links.ui.screens.ItemsGraph
import com.kbcoding.l51_nav_component_deep_links.ui.screens.LocalNavController
import com.kbcoding.l51_nav_component_deep_links.ui.screens.ProfileGraph
import com.kbcoding.l51_nav_component_deep_links.ui.screens.SettingsGraph
import com.kbcoding.l51_nav_component_deep_links.ui.screens.addItem.AddItemScreen
import com.kbcoding.l51_nav_component_deep_links.ui.screens.edit.EditItemScreen
import com.kbcoding.l51_nav_component_deep_links.ui.screens.items.ItemsScreen
import com.kbcoding.l51_nav_component_deep_links.ui.screens.profile.ProfileScreen
import com.kbcoding.l51_nav_component_deep_links.ui.screens.routeClass
import com.kbcoding.l51_nav_component_deep_links.ui.screens.settings.SettingsScreen
import com.kbcoding.l51_nav_component_deep_links.ui.theme.AndroidComposeBasicsTheme
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
        ItemsGraph.RouteItems::class -> R.string.items_screen
        ItemsGraph.RouteAddItem::class -> R.string.add_item_screen
        ItemsGraph.RouteEditItem::class -> R.string.add_item_screen
        SettingsGraph.RouteSettings::class -> R.string.settings_screen
        ProfileGraph.RouteProfile::class -> R.string.profile_screen
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
            if (currentBackStackEntry?.routeClass() == ItemsGraph.RouteItems::class) {
                FloatingActionButton(
                    onClick = { navController.navigate(ItemsGraph.RouteAddItem) }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        },
        bottomBar = {
            AppNavigationBar(navController = navController, tabs = MainTabs)
        }
    ) { paddingValues ->
        CompositionLocalProvider(
            value = LocalNavController provides navController
        ) {
            NavHost(
                navController = navController,
                startDestination = ProfileGraph,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                navigation<ItemsGraph>(startDestination = ItemsGraph.RouteItems) {
                    composable<ItemsGraph.RouteItems> { ItemsScreen() }
                    composable<ItemsGraph.RouteAddItem> { AddItemScreen() }
                    composable<ItemsGraph.RouteEditItem> { navBackStackEntry ->
                        val route: ItemsGraph.RouteEditItem = navBackStackEntry.toRoute()
                        EditItemScreen(index = route.index)
                    }
                }
                navigation<SettingsGraph>(startDestination = SettingsGraph.RouteSettings) {
                    composable<SettingsGraph.RouteSettings> { SettingsScreen() }
                }
                navigation<ProfileGraph>(startDestination = ProfileGraph.RouteProfile) {
                    composable<ProfileGraph.RouteProfile> { ProfileScreen() }
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