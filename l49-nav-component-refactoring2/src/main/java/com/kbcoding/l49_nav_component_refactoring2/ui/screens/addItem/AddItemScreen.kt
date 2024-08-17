package com.kbcoding.l49_nav_component_refactoring2.ui.screens.addItem

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.kbcoding.l49_nav_component_refactoring2.EventConsumer
import com.kbcoding.l49_nav_component_refactoring2.R
import com.kbcoding.l49_nav_component_refactoring2.ui.components.ItemDetails
import com.kbcoding.l49_nav_component_refactoring2.ui.components.ItemDetailsState
import com.kbcoding.l49_nav_component_refactoring2.ui.screens.RouteAddItem
import com.kbcoding.l49_nav_component_refactoring2.ui.screens.LocalNavController
import com.kbcoding.l49_nav_component_refactoring2.ui.screens.routeClass

@Composable
fun AddItemScreen(
    modifier: Modifier = Modifier
) {
    val navController = LocalNavController.current
    val viewModel = hiltViewModel<AddItemViewModel>()
    val screenState by viewModel.stateFlow.collectAsState()

    AddItemContent(
        screenState = screenState,
        onAddButtonClicked = viewModel::addItem
    )

    EventConsumer(
        channel = viewModel.exitChannel,
        block = {
            if (navController.currentBackStackEntry?.routeClass() == RouteAddItem::class) {
                navController.popBackStack()
            }
        }
    )

}

@Composable
fun AddItemContent(
    screenState: AddItemViewModel.ScreenState,
    onAddButtonClicked: (String) -> Unit,
) {

    ItemDetails(
        state = ItemDetailsState(
            loadedItem = "",
            textFieldPlaceholder = stringResource(id = R.string.enter_new_item),
            actionButtonText = stringResource(id = R.string.add),
            isActionInProgress = screenState.isProgressVisible
        ),
        onActionButtonClicked = onAddButtonClicked,
        modifier = Modifier.fillMaxSize()
    )

}