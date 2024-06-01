package com.kbcoding.l42_nav_view_models_hilt.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kbcoding.l42_nav_view_models_hilt.MainViewModel
import com.kbcoding.l42_nav_view_models_hilt.di.injectViewModel
import com.kbcoding.l42_nav_view_models_hilt.ui.scaffold.AppFloatingActionButton
import com.kbcoding.l42_nav_view_models_hilt.ui.scaffold.AppNavigationBar
import com.kbcoding.l42_nav_view_models_hilt.ui.scaffold.AppToolbar
import com.kbcoding.navigationstack.navigation.rememberNavigation

/**
 * App UI skeleton containing top toolbar, bottom nav-bar,
 * floating action button and screen content.
 */
@Composable
@Preview(showSystemUi = true)
fun AppScaffold() {
    val viewModel = injectViewModel<MainViewModel>()
    val navigation = rememberNavigation(RootTabs, deepLinkHandler = AppDeepLinkHandler)
    val (router, navigationState) = navigation
    val environment = navigationState.currentScreen.environment as AppScreenEnvironment
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppToolbar(
                titleRes = environment.titleRes,
                isRoot = navigationState.isRoot,
                onPopAction = router::pop,
                onClearAction = viewModel::clear,
            )
        },
        floatingActionButton = {
            AppFloatingActionButton(
                floatingAction = environment.floatingAction,
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            AppNavigationBar(
                currentIndex = navigationState.currentStackIndex,
                onIndexSelected = router::switchStack,
            )
        }
    ) { paddingValues ->
        AppNavigationHost(
            modifier = Modifier.padding(paddingValues),
            navigation = navigation,
        )
    }
}