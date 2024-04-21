package com.kbcoding.navigationstack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.kbcoding.navigationstack.navigation.internal.InternalNavigationState
import com.kbcoding.navigationstack.navigation.internal.ScreenStack

@Stable
data class Navigation internal constructor(
    val router: Router,
    val navigationState: NavigationState,
    internal val internalNavigationState: InternalNavigationState,
)

@Composable
fun rememberNavigation(initialRoute: Route): Navigation {
    val screenStack = rememberSaveable {
        ScreenStack(mutableStateListOf(initialRoute))
    }
    return remember(initialRoute) {
        Navigation(
            router = screenStack,
            navigationState = screenStack,
            internalNavigationState = screenStack
        )
    }
}
