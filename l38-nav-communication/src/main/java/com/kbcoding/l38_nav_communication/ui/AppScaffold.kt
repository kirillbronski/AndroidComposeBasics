package com.kbcoding.l38_nav_communication.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kbcoding.l38_nav_communication.ItemsRepository
import com.kbcoding.l38_nav_communication.ui.scaffold.AppFloatingActionButton
import com.kbcoding.l38_nav_communication.ui.scaffold.AppNavigationBar
import com.kbcoding.l38_nav_communication.ui.scaffold.AppToolbar
import com.kbcoding.navigationstack.navigation.rememberNavigation

/**
 * App UI skeleton containing top toolbar, bottom nav-bar,
 * floating action button and screen content.
 */
@Composable
@Preview(showSystemUi = true)
fun AppScaffold() {
    val itemsRepository = ItemsRepository.get()
    val navigation = rememberNavigation(AppRoute.Tab.Items)
    val (router, navigationState) = navigation
    val environment = navigationState.currentScreen.environment as AppScreenEnvironment
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppToolbar(
                titleRes = environment.titleRes,
                isRoot = navigationState.isRoot,
                onPopAction = router::pop,
                onClearAction = itemsRepository::clear,
            )
        },
        floatingActionButton = {
            AppFloatingActionButton(
                floatingAction = environment.floatingAction,
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            if (navigationState.isRoot) {
                AppNavigationBar(
                    currentRoute = navigationState.currentRoute,
                    onRouteSelected = router::restart,
                )
            }
        }
    ) { paddingValues ->
        AppNavigationHost(
            modifier = Modifier.padding(paddingValues),
            navigation = navigation,
        )
    }
}