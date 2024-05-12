package com.kbcoding.navigationstack.navigation.internal

import com.kbcoding.navigationstack.navigation.Route
import com.kbcoding.navigationstack.navigation.Router

internal object EmptyRouter : Router {
    override fun launch(route: Route) = Unit
    override fun pop(response: Any?) = Unit
    override fun restart(rootRoutes: List<Route>, initialIndex: Int) = Unit
    override fun switchStack(index: Int) = Unit
}