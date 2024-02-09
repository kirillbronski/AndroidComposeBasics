package com.kbcoding.l18_compositionlocal.controller

import com.kbcoding.l18_compositionlocal.data.AppTheme
import com.kbcoding.l18_compositionlocal.data.ThemeDataSource


class RealThemeController(
    private val themeDataSource: ThemeDataSource,
) : AppThemeController {

    override fun toggle() {
        val currentTheme = themeDataSource.themeStateFlow.value
        if (currentTheme == AppTheme.Dark) {
            themeDataSource.setTheme(AppTheme.Light)
        } else {
            themeDataSource.setTheme(AppTheme.Dark)
        }
    }

}
