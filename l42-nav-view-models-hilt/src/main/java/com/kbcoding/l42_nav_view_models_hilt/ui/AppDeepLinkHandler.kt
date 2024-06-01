package com.kbcoding.l42_nav_view_models_hilt.ui

import android.net.Uri
import com.kbcoding.l42_nav_view_models_hilt.ui.screens.item.ItemScreenArgs
import com.kbcoding.navigationstack.navigation.links.DeepLinkHandler
import com.kbcoding.navigationstack.navigation.links.MultiStackState

object AppDeepLinkHandler : DeepLinkHandler {

    override fun handleDeepLink(
        uri: Uri,
        inputState: MultiStackState
    ): MultiStackState {
        var outputState = inputState
        if (uri.scheme == "nav") {
            if (uri.host == "settings") {
                outputState = inputState.copy(activeStackIndex = 1)
            } else if (uri.host == "items") {
                val itemIndex = uri.pathSegments?.firstOrNull()?.toIntOrNull()
                if (itemIndex != null) {
                    val editItemRoute = AppRoute.Item(ItemScreenArgs.Edit(itemIndex))
                    outputState = outputState.withNewRoute(stackIndex = 0, editItemRoute)
                }
            }
        }
        return outputState
    }
}