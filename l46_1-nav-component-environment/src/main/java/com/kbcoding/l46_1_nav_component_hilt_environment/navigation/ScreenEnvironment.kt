package com.kbcoding.l46_1_nav_component_hilt_environment.navigation

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector

@Stable
interface ScreenEnvironment {
    val titleRes: Int
    val floatingAction: FloatingAction?
}

@Immutable
data class FloatingAction(
    val icon: ImageVector,
    val onClick: () -> Unit
)

// ---

class ScreenEnvironmentImpl : ScreenEnvironment {
    override var titleRes: Int = 0
    override var floatingAction: FloatingAction? = null

    companion object {
        val DEFAULT = ScreenEnvironmentImpl()
    }
}