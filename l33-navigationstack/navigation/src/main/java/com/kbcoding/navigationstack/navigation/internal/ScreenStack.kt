package com.kbcoding.navigationstack.navigation.internal

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.kbcoding.navigationstack.navigation.NavigationState
import com.kbcoding.navigationstack.navigation.Route
import com.kbcoding.navigationstack.navigation.Router

internal class ScreenStack(
    private val routes: SnapshotStateList<Route>
) : NavigationState, Router {

    override val isRoot: Boolean
        get() = routes.size == 1

    override val currentRoute: Route
        get() = routes.last()

    override fun launch(route: Route) {
        routes.add(route)
    }

    override fun pop() {
        routes.removeLastOrNull()
    }

    override fun restart(route: Route) {
        routes.apply {
            clear()
            add(route)
        }
    }
}