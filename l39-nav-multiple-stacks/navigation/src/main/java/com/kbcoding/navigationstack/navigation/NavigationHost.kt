package com.kbcoding.navigationstack.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import com.kbcoding.navigationstack.navigation.internal.EmptyRouter
import com.kbcoding.navigationstack.navigation.internal.NavigationEvent
import kotlinx.coroutines.flow.filterIsInstance


/**
 * CompositionLocal to access a [Router] instance from individual screens managed
 * by [NavigationHost].
 */
val LocalRouter = staticCompositionLocalOf<Router> {
    EmptyRouter
}

/**
 * Component for displaying routes which are managed by the specified [Navigation].
 */
@Composable
fun NavigationHost(
    navigation: Navigation,
    modifier: Modifier = Modifier,
) {
    val (
        router,
        navigationState,
        internalState,
    ) = navigation
    BackHandler(enabled = !navigationState.isRoot) {
        router.pop()
    }
    val saveableStateHolder = rememberSaveableStateHolder()
    saveableStateHolder.SaveableStateProvider(key = internalState.currentUuid) {
        Box(modifier = modifier) {
            CompositionLocalProvider(
                LocalRouter provides router,
                LocalScreenResponseReceiver provides internalState.screenResponseReceiver
            ) {
                navigationState.currentScreen.Content()
            }
        }
    }
    LaunchedEffect(navigation) {
        navigation.internalNavigationState.listen()
            .filterIsInstance<NavigationEvent.Removed>()
            .collect { event ->
                saveableStateHolder.removeState(event.routeRecord.uuid)
            }
    }
}
