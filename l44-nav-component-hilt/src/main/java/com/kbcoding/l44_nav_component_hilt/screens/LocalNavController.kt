package com.kbcoding.l44_nav_component_hilt.screens

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

val LocalNavController =
    staticCompositionLocalOf<NavController> { error("Can't access NavController") }