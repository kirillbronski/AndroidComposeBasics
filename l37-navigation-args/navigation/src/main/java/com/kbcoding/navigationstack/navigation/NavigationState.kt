package com.kbcoding.navigationstack.navigation

import androidx.compose.runtime.Stable

/**
 * Represents the current state of navigation.
 */
@Stable
interface NavigationState {

    /**
     * Whether there is only one screen in the stack.
     */
    val isRoot: Boolean

    /**
     * Current route representing the visible screen to the user
     * (which is located at the top of screen stack).
     */
    val currentRoute: Route

    /**
     * Current visible to the user screen.
     */
    val currentScreen: Screen
}