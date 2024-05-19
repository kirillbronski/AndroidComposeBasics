package com.kbcoding.navigationstack.navigation.internal

import com.kbcoding.navigationstack.navigation.ScreenResponseReceiver
import kotlinx.coroutines.flow.Flow

internal sealed class NavigationEvent {
    data class Removed(val routeRecord: RouteRecord) : NavigationEvent()
}

internal interface InternalNavigationState {
    val currentUuid: String
    val screenResponseReceiver: ScreenResponseReceiver
    fun listen(): Flow<NavigationEvent>
}
