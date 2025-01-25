package com.kbcoding.l37_navigation_args.ui

import com.kbcoding.l37_navigation_args.ui.screens.ItemScreenArgs
import com.kbcoding.l37_navigation_args.ui.screens.ItemsScreenProducer
import com.kbcoding.l37_navigation_args.ui.screens.ProfileScreenProducer
import com.kbcoding.l37_navigation_args.ui.screens.SettingsScreenProducer
import com.kbcoding.l37_navigation_args.ui.screens.itemScreenProducer
import com.kbcoding.navigationstack.navigation.Route
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
    listOf(AppRoute.Tab.Items, AppRoute.Tab.Settings, AppRoute.Tab.Profile)