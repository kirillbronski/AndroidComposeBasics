package com.kbcoding.l18_compositionlocal.data

import androidx.compose.ui.graphics.Color
import com.kbcoding.l18_compositionlocal.AppThemeContainer

/**
 * Predefined colors for app widgets placed into [AppThemeContainer].
 */
data class AppTheme(
    val buttonColor: Color,
    val bgColor: Color,
) {
    companion object {
        val Light = AppTheme(
            buttonColor = Color.Blue,
            bgColor = Color.White,
        )
        val Dark = AppTheme(
            buttonColor = Color.Gray,
            bgColor = Color.Black,
        )
    }
}
