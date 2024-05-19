package com.kbcoding.navigationstack.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.platform.LocalContext
import com.kbcoding.navigationstack.navigation.internal.InternalNavigationState
import com.kbcoding.navigationstack.navigation.internal.ScreenMultiStack
import com.kbcoding.navigationstack.navigation.internal.ScreenStack
import com.kbcoding.navigationstack.navigation.links.DeepLinkHandler
import com.kbcoding.navigationstack.navigation.links.MultiStackState
import com.kbcoding.navigationstack.navigation.links.StackState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

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
    deepLinkHandler: DeepLinkHandler = DeepLinkHandler.DEFAULT
): Navigation {
    val activity = LocalContext.current as? Activity
    val screenStack = rememberSaveable(rootRoutes) {
        val inputState = MultiStackState(
            activeStackIndex = initialIndex,
            stacks = rootRoutes.map { rootRoute ->
                StackState(listOf(rootRoute))
            }
        )

        val outputState = activity?.intent?.data?.let { deepLinkUri ->
            deepLinkHandler.handleDeepLink(deepLinkUri, inputState)
        } ?: inputState

        ScreenMultiStack(
            initialIndex = outputState.activeStackIndex,
            stacks = outputState.stacks.map { stackState ->
                ScreenStack(stackState.routes)
            }.toMutableStateList()
        )
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
