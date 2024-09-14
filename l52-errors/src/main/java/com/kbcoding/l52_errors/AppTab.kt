package com.kbcoding.l52_errors

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.kbcoding.l52_errors.ui.screens.ItemsGraph
import com.kbcoding.l52_errors.ui.screens.ProfileGraph
import com.kbcoding.l52_errors.ui.screens.SettingsGraph
import kotlinx.collections.immutable.persistentListOf

data class AppTab(
    val icon: ImageVector,
    @StringRes val labelRes: Int,
    val graph: Any,
)

val MainTabs = persistentListOf(
    AppTab(
        icon = Icons.Default.AccountBox,
        labelRes = R.string.profile_screen,
        graph = ProfileGraph,
    ),
    AppTab(
        icon = Icons.AutoMirrored.Default.List,
        labelRes = R.string.items_screen,
        graph = ItemsGraph,
    ),
    AppTab(
        icon = Icons.Default.Settings,
        labelRes = R.string.settings_screen,
        graph = SettingsGraph,
    )
)