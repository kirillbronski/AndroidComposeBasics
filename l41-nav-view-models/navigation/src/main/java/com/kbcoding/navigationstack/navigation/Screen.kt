package com.kbcoding.navigationstack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable

/**
 * Represents a single screen within the app launched by a specific [Route].
 */
@Stable
interface Screen {

    /**
     * Configuration for components located outside of the screen
     * (toolbars, FABs, etc.)
     */
    val environment: ScreenEnvironment

    /**
     * Screen content which is displayed when the screen is at the top of screen stack.
     */
    @Composable
    fun Content()

}