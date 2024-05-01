package com.kbcoding.l36_navigation_environment.ui.scaffold

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kbcoding.l36_navigation_environment.ui.AppRoute
import com.kbcoding.l36_navigation_environment.ui.RootTabs
import com.kbcoding.navigationstack.navigation.Route

/**
 * In-app bottom navigation bar,
 */
@Composable
fun AppNavigationBar(
    currentRoute: Route,
    onRouteSelected: (Route) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        RootTabs.forEach { tab ->
            val environment = remember(tab) {
                tab.screenProducer().environment
            }
            val icon = environment.icon
            if (icon != null) {
                NavigationBarItem(
                    selected = currentRoute == tab,
                    label = { Text(stringResource(environment.titleRes)) },
                    onClick = { onRouteSelected(tab) },
                    icon = {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun AppNavigationBarPreview() {
    AppNavigationBar(
        currentRoute = AppRoute.Tab.Settings,
        onRouteSelected = {},
    )
}