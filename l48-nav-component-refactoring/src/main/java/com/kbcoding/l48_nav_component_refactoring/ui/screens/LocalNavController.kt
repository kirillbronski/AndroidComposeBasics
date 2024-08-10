package com.kbcoding.l48_nav_component_refactoring.ui.screens

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

val LocalNavController =
    staticCompositionLocalOf<NavController> { error("Can't access NavController") }