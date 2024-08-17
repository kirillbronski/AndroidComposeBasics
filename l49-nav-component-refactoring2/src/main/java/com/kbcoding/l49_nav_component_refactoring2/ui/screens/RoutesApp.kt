package com.kbcoding.l49_nav_component_refactoring2.ui.screens

import androidx.navigation.NavBackStackEntry
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass


@Serializable
data object RouteItems

@Serializable
data object RouteAddItem

@Serializable
data class RouteEditItem(val index: Int)


// ---

fun NavBackStackEntry?.routeClass(): KClass<*>? {
    return this?.destination?.route
        ?.split("/")
        ?.first()
        ?.let { Class.forName(it) }
        ?.kotlin
}
