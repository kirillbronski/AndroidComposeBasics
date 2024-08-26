package com.kbcoding.l50_nav_component_multistack.ui.screens

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

val LocalNavController =
    staticCompositionLocalOf<NavController> { error("Can't access NavController") }