package com.kbcoding.navigationstack.navigation.links

import com.kbcoding.navigationstack.navigation.Route

data class MultiStackState(
    val stacks: List<StackState>,
    val activeStackIndex: Int,
) {

    fun withNewRoute(
        stackIndex: Int,
        route: Route,
    ): MultiStackState {
        val modifiedStacks = stacks.toMutableList()
        modifiedStacks[stackIndex] = modifiedStacks[stackIndex].withNewRoute(route)
        return copy(
            activeStackIndex = stackIndex,
            stacks = modifiedStacks
        )
    }
}
