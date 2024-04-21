package com.kbcoding.navigationstack.navigation.internal

import com.kbcoding.navigationstack.navigation.Route
import kotlinx.coroutines.flow.Flow

internal sealed class NavigationEvent {
    data class Removed(val route: Route) : NavigationEvent()
}

internal interface InternalNavigationState {
    fun listen(): Flow<NavigationEvent>
}