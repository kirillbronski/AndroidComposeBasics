package com.kbcoding.navigationstack.navigation.links

import com.kbcoding.navigationstack.navigation.Route

data class StackState(
    val routes: List<Route>
) {

    fun withNewRoute(route: Route): StackState = copy(
        routes = routes + route
    )
}
