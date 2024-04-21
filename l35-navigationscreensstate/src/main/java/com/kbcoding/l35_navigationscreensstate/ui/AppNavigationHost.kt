package com.kbcoding.l35_navigationscreensstate.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kbcoding.l35_navigationscreensstate.ui.screens.AddItemScreen
import com.kbcoding.l35_navigationscreensstate.ui.screens.ItemsScreen
import com.kbcoding.l35_navigationscreensstate.ui.screens.ProfileScreen
import com.kbcoding.l35_navigationscreensstate.ui.screens.SettingsScreen
import com.kbcoding.navigationstack.navigation.Navigation
import com.kbcoding.navigationstack.navigation.NavigationHost

@Composable
fun AppNavigationHost(
    navigation: Navigation,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    NavigationHost(
        navigation = navigation,
        modifier = modifier.padding(paddingValues)
    ) { currentRoute ->
        // screen content:
        when (currentRoute) {
            AppRoute.Tab.Items -> ItemsScreen()
            AppRoute.Tab.Settings -> SettingsScreen()
            AppRoute.Tab.Profile -> ProfileScreen()
            AppRoute.AddItem -> AddItemScreen()
        }
    }
}