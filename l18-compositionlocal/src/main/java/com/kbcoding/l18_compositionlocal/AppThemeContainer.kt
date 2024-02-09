package com.kbcoding.l18_compositionlocal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kbcoding.l18_compositionlocal.controller.AppThemeController
import com.kbcoding.l18_compositionlocal.controller.EmptyThemeController
import com.kbcoding.l18_compositionlocal.controller.RealThemeController
import com.kbcoding.l18_compositionlocal.data.AppTheme
import com.kbcoding.l18_compositionlocal.data.SharedPreferencesThemeDataSource

/**
 * Provides the current app theme.
 * @see AppTheme
 */
val LocalAppTheme = compositionLocalOf<AppTheme> {
    AppTheme.Light
}

/**
 * Provides the current app theme controller which can toggle an app theme.
 * @see [AppThemeController]
 */
val LocalAppThemeController = staticCompositionLocalOf<AppThemeController> {
    EmptyThemeController
}

/**
 * Base container for components which render itself by using app theme
 * provided by [LocalAppTheme] provider.
 */
@Composable
fun AppThemeContainer(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val themeDataSource = remember {
        SharedPreferencesThemeDataSource(context)
    }
    val controller = remember {
        RealThemeController(themeDataSource)
    }
    val theme by themeDataSource.themeStateFlow.collectAsStateWithLifecycle()
    CompositionLocalProvider(
        LocalAppTheme provides theme,
        LocalAppThemeController provides controller,
    ) {
        content()
    }
}
