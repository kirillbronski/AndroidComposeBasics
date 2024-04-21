package com.kbcoding.l35_navigationscreensstate.ui.scaffold

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kbcoding.l35_navigationscreensstate.ui.AppRoute
import com.kbcoding.l35_navigationscreensstate.ui.RootTabs
import com.kbcoding.navigationstack.navigation.NavigationState

@Composable
fun AppNavigationBar(
    navigationState: NavigationState,
    onClick: (AppRoute.Tab) -> Unit,
    modifier: Modifier = Modifier
) {
    if (navigationState.isRoot) {
        NavigationBar {
            // render NavigationBarItem for each tab route:
            RootTabs.forEach { tab ->
                NavigationBarItem(
                    selected = navigationState.currentRoute == tab,
                    label = { Text(stringResource(tab.titleRes)) },
                    onClick = { onClick(tab) },
                    icon = {
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = stringResource(tab.titleRes)
                        )
                    }
                )
            }
        }
    }
}