package com.kbcoding.l47_nav_component_hilt_args.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.navigation.NavDestinationDsl
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph

@Stable
interface Navigation {
    val navGraph: NavGraph
    val environment: ScreenEnvironment
    val navController: NavHostController
}

@Composable
fun rememberNavigation(
    startDestination: String,
    builder: CustomNavGraphBuilder.() -> Unit
): Navigation {
    val navController: NavHostController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: ""

    val environmentStore = remember { EnvironmentStore() }
    val environment = remember(currentRoute) {
        environmentStore[currentRoute].invoke()
    }
    val environmentState = rememberUpdatedState(newValue = environment)

    return remember {
        val navGraph = navController.createGraph(startDestination) {
            val navGraphBuilder: NavGraphBuilder = this
            val customNavGraphBuilder = CustomNavGraphBuilderImpl(
                navGraphBuilder = navGraphBuilder,
                environmentStore = environmentStore,
                navController = navController,
            )
            customNavGraphBuilder.apply(builder)
        }
        NavigationImpl(
            navController = navController,
            environmentState = environmentState,
            navGraph = navGraph,
        )
    }
}

@NavDestinationDsl
interface CustomNavGraphBuilder {
    fun screen(route: String, block: CustomScreenBuilder.() -> Unit)
}

interface CustomScreenBuilder {
    fun content(block: @Composable () -> Unit)
    fun environment(block: ScreenEnvironmentBuilder.() -> Unit)
}

interface ScreenEnvironmentBuilder {
    var titleRes: Int
    var floatingAction: FloatingAction?
    val navController: NavHostController
}

// ---

class NavigationImpl(
    override val navController: NavHostController,
    override val navGraph: NavGraph,
    private val environmentState: State<ScreenEnvironment>
) : Navigation {
    override val environment: ScreenEnvironment get() = environmentState.value
}

class EnvironmentStore {
    private val environmentProviders = mutableMapOf<String, () -> ScreenEnvironment>()

    operator fun set(route: String, environmentProvider: () -> ScreenEnvironment) {
        environmentProviders[route] = environmentProvider
    }

    operator fun get(route: String) = environmentProviders[route]
        ?: { ScreenEnvironmentImpl.DEFAULT }

}

// ---

class CustomNavGraphBuilderImpl(
    private val navGraphBuilder: NavGraphBuilder,
    private val environmentStore: EnvironmentStore,
    private val navController: NavHostController,
) : CustomNavGraphBuilder {
    override fun screen(route: String, block: CustomScreenBuilder.() -> Unit) {
        val builder = CustomScreenBuilderImpl(
            route = route,
            navGraphBuilder = navGraphBuilder,
            environmentStore = environmentStore,
            navController = navController,
        )
        builder.apply(block)
    }
}

class CustomScreenBuilderImpl(
    private val route: String,
    private val navGraphBuilder: NavGraphBuilder,
    private val environmentStore: EnvironmentStore,
    private val navController: NavHostController,
) : CustomScreenBuilder {
    override fun content(block: @Composable () -> Unit) {
        navGraphBuilder.composable(route) { block() }
    }

    override fun environment(block: ScreenEnvironmentBuilder.() -> Unit) {
        val environmentProvider = {
            val screenEnvironment = ScreenEnvironmentImpl()
            val builder = ScreenEnvironmentBuilderImpl(
                screenEnvironment = screenEnvironment,
                navController = navController,
            )
            builder.apply(block)
            screenEnvironment
        }
        environmentStore[route] = environmentProvider
    }
}


class ScreenEnvironmentBuilderImpl(
    private val screenEnvironment: ScreenEnvironmentImpl,
    override val navController: NavHostController,
) : ScreenEnvironmentBuilder {
    override var titleRes: Int by screenEnvironment::titleRes
    override var floatingAction: FloatingAction? by screenEnvironment::floatingAction
}