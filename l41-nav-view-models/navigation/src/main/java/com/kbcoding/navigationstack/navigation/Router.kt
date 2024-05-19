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
    fun pop(response: Any? = null)

    /**
     * Remove all screens from the navigation stack and launch
     * the specified [route].
     */
    fun restart(route: Route) = restart(listOf(route))

    /**
     * Remove all existing navigation stacks and create new ones.
     * Each stack will be initialized with the root screen specified by the
     * list of [rootRoutes]. The number of stacks will be equal to list size.
     * @param initialIndex index of the navigation stack to be displayed in the [NavigationHost].
     */
    fun restart(rootRoutes: List<Route>, initialIndex: Int = 0)

    /**
     * Change the current active navigation stack which should be
     * displayed in the [NavigationHost].
     */
    fun switchStack(index: Int)

}