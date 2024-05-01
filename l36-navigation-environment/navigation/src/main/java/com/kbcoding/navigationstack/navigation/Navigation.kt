package com.kbcoding.navigationstack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.kbcoding.navigationstack.navigation.internal.InternalNavigationState
import com.kbcoding.navigationstack.navigation.internal.RouteRecord
import com.kbcoding.navigationstack.navigation.internal.ScreenStack

/**
 * Entry point to the navigation stuff. Use [rememberNavigation] in order
 * to create an instance of this class.
 */
@Stable
data class Navigation internal constructor(
    val router: Router,
    val navigationState: NavigationState,
    internal val internalNavigationState: InternalNavigationState,
)

/**
 * Create and remember a new [Navigation] instance.
 * @param initialRoute starting screen to be displayed in the [NavigationHost].
 */
@Composable
fun rememberNavigation(initialRoute: Route): Navigation {
    val screenStack = rememberSaveable(initialRoute) {
        ScreenStack(mutableStateListOf(RouteRecord(initialRoute)))
    }

    return remember(initialRoute) {
        Navigation(
            router = screenStack,
            navigationState = screenStack,
            internalNavigationState = screenStack,
        )
    }
}
