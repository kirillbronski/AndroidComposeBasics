package com.kbcoding.l42_nav_view_models_hilt.ui

import com.kbcoding.l42_nav_view_models_hilt.ui.screens.item.ItemScreenArgs
import com.kbcoding.l42_nav_view_models_hilt.ui.screens.item.itemScreenProducer
import com.kbcoding.l42_nav_view_models_hilt.ui.screens.items.ItemsScreenProducer
import com.kbcoding.l42_nav_view_models_hilt.ui.screens.ProfileScreenProducer
import com.kbcoding.l42_nav_view_models_hilt.ui.screens.SettingsScreenProducer
import com.kbcoding.navigationstack.navigation.Route
import kotlinx.collections.immutable.persistentListOf
import kotlinx.parcelize.Parcelize

/**
 * AppRoute represents all available routes within this application.
 */
sealed class AppRoute(
    override val screenProducer: () -> AppScreen,
) : Route {

    @Parcelize
    data class Item(
        val args: ItemScreenArgs
    ) : AppRoute(itemScreenProducer(args))

    sealed class Tab(
        screenProducer: () -> AppScreen,
    ) : AppRoute(screenProducer) {
        @Parcelize
        data object Items : Tab(ItemsScreenProducer)

        @Parcelize
        data object Settings : Tab(SettingsScreenProducer)

        @Parcelize
        data object Profile : Tab(ProfileScreenProducer)
    }
}

/**
 * The list of all root tabs.
 */
val RootTabs =
    persistentListOf(AppRoute.Tab.Items, AppRoute.Tab.Settings, AppRoute.Tab.Profile)