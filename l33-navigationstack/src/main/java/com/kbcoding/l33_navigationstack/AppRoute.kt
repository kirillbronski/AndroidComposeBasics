package com.kbcoding.l33_navigationstack

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.kbcoding.navigationstack.navigation.Route

sealed class AppRoute(

    /**
     * Screen title will be displayed in the app toolbar and in the bottom
     * navigation bar.
     */
    @StringRes val titleRes: Int

): Route {

    /**
     * "Add New Item" screen.
     */
    object AddItem : AppRoute(R.string.add_item)

    /**
     * Screens that can be displayed in the bottom navigation bar.
     */
    sealed class Tab(

        @StringRes titleRes: Int,

        /**
         * Additional icon for tabs within bottom navigation bar
         */
        val icon: ImageVector,

        ) : AppRoute(titleRes) {

        object Items : Tab(R.string.items, Icons.Default.List)
        object Settings : Tab(R.string.settings, Icons.Default.Settings)
        object Profile : Tab(R.string.profile, Icons.Default.AccountBox)

    }
}