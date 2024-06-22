package com.kbcoding.l44_nav_component_hilt.screens

sealed class Route(val path: String) {
    data object Items : Route("items")
    data object AddItem : Route("add-item")

    companion object {
        fun fromPath(path: String): Route {
            return when (path) {
                Items.path -> Items
                AddItem.path -> AddItem
                else -> throw IllegalArgumentException("Path $path is not recognized.")
            }
        }
    }
}