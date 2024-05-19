package com.kbcoding.l41_nav_view_models.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kbcoding.navigationstack.navigation.Navigation
import com.kbcoding.navigationstack.navigation.NavigationHost
import com.kbcoding.navigationstack.navigation.rememberNavigation

/**
 * Wrapper for the [NavigationHost] with additional mapping between
 * routes and screen composable functions.
 */
@Composable
fun AppNavigationHost(
    navigation: Navigation,
    modifier: Modifier = Modifier,
) {
    NavigationHost(
        modifier = modifier,
        navigation = navigation,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun AppNavigationHostPreview() {
    AppNavigationHost(
        navigation = rememberNavigation(RootTabs),
    )
}