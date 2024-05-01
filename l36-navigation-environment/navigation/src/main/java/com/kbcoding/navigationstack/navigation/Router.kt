package com.kbcoding.navigationstack.navigation

import androidx.compose.runtime.Stable

/**
 * Controller for executing nav actions: launch, pop and restart.
 */
@Stable
interface Router {

    /**
     * Launch a new screen and place it at the top of screen stack.
     */
    fun launch(route: Route)

    /**
     * Close the current screen and go to the previous one.
     */
    fun pop()

    /**
     * Remove all screens from the navigation stack and launch
     * the specified [route].
     */
    fun restart(route: Route)

}