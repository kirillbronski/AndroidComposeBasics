package com.kbcoding.l43_nav_component.screens

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

val LocalNavController =
    staticCompositionLocalOf<NavController> { error("Can't access NavController") }