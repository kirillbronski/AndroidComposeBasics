package com.kbcoding.navigationstack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.kbcoding.navigationstack.navigation.internal.InternalNavigationState
import com.kbcoding.navigationstack.navigation.internal.ScreenMultiStack
import com.kbcoding.navigationstack.navigation.internal.ScreenStack
import kotlinx.collections.immutable.ImmutableList

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
 * @param rootRoutes the list of routes to be placed as root screens to each navigation stack
 * @param initialIndex the index of navigation stack that will be displayed to the user in the [NavigationHost].
 */
@Composable
fun rememberNavigation(
    rootRoutes: ImmutableList<Route>,
    initialIndex: Int = 0,
): Navigation {
    val screenStack = rememberSaveable(rootRoutes) {
        val stacks = SnapshotStateList<ScreenStack>()
        stacks.addAll(rootRoutes.map(::ScreenStack))
        ScreenMultiStack(stacks, initialIndex)
    }

    return remember(rootRoutes) {
        Navigation(
            router = screenStack,
            navigationState = screenStack,
            internalNavigationState = screenStack,
        )
    }
}

//@Composable
//fun rememberNavigation(
//    initialRoute: Route,
//): Navigation = rememberNavigation(persistentListOf(initialRoute))
