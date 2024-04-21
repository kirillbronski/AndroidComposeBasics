package com.kbcoding.l35_navigationscreensstate.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.kbcoding.l35_navigationscreensstate.ItemsRepository
import com.kbcoding.l35_navigationscreensstate.ui.scaffold.AppFloatingActionButton
import com.kbcoding.l35_navigationscreensstate.ui.scaffold.AppNavigationBar
import com.kbcoding.l35_navigationscreensstate.ui.scaffold.AppToolbar
import com.kbcoding.navigationstack.navigation.rememberNavigation

/**
 * The list of all root tabs.
 */
val RootTabs =
    listOf(AppRoute.Tab.Items, AppRoute.Tab.Settings, AppRoute.Tab.Profile)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(itemsRepository: ItemsRepository = ItemsRepository.get()) {

    val navigation = rememberNavigation(initialRoute = AppRoute.Tab.Items)
    val (router, navigationState) = navigation

    Scaffold(
        topBar = {
            AppToolbar(navigationState, router, itemsRepository)
        },
        floatingActionButton = {
            if (navigationState.currentRoute == AppRoute.Tab.Items) {
                // floating action button is displayed only for 1 screen - Route.Tab.Items
                AppFloatingActionButton(
                    onClick = {
                        router.launch(AppRoute.AddItem)
                    }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            // bottom navigation bar is hidden if more than one
            // screen is located in the back stack:
            AppNavigationBar(
                navigationState = navigationState,
                onClick = {
                    router.restart(it)
                }
            )
        },
    ) { paddingValues ->
        AppNavigationHost(navigation = navigation, paddingValues = paddingValues)
    }
}