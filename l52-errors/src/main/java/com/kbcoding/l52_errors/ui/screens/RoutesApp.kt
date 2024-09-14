package com.kbcoding.l52_errors.ui.screens

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.navDeepLink
import kotlinx.serialization.Serializable
import java.util.regex.Pattern
import kotlin.reflect.KClass

@Serializable
data object ItemsGraph {

    @Serializable
    data object RouteItems

    @Serializable
    data object RouteAddItem

    @Serializable
    data class RouteEditItem(
        val index: Int
    ) {
        companion object {
            val link = navDeepLink {
                uriPattern = "nav://items/{index}"
            }
        }
    }
}

@Serializable
data object SettingsGraph {

    val link = navDeepLink {
        uriPattern = "nav://settings"
    }

    @Serializable
    data object RouteSettings
}

@Serializable
data object ProfileGraph {

    @Serializable
    data object RouteProfile
}

// ---

fun NavBackStackEntry?.routeClass(): KClass<*>? {
    return this?.destination.routeClass()
}

fun NavDestination?.routeClass(): KClass<*>? {
    return this?.route?.substringBefore("/")?.let { className ->
        generateSequence(className, ::replaceLastDotByDollar)
            .mapNotNull(::tryParseClass)
            .firstOrNull()
    }
}

private fun tryParseClass(className: String): KClass<*>? {
    return runCatching { Class.forName(className).kotlin }.getOrNull()
}

private val regexPattern = Pattern.compile("\\.([^.]+)\$")

private fun replaceLastDotByDollar(input: String): String? {
    val index = input.lastIndexOf('.')
    return if (index != -1) {
        regexPattern.matcher(input).replaceFirst("\\$$1")
    } else {
        null
    }
}
