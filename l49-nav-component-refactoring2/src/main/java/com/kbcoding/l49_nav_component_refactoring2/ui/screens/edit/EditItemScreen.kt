package com.kbcoding.l49_nav_component_refactoring2.ui.screens.edit

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kbcoding.l49_nav_component_refactoring2.R
import com.kbcoding.l49_nav_component_refactoring2.EventConsumer
import com.kbcoding.l49_nav_component_refactoring2.data.LoadResult
import com.kbcoding.l49_nav_component_refactoring2.ui.components.ItemDetails
import com.kbcoding.l49_nav_component_refactoring2.ui.components.ItemDetailsState
import com.kbcoding.l49_nav_component_refactoring2.ui.components.LoadResultContent
import com.kbcoding.l49_nav_component_refactoring2.ui.screens.LocalNavController
import com.kbcoding.l49_nav_component_refactoring2.ui.screens.RouteEditItem
import com.kbcoding.l49_nav_component_refactoring2.ui.screens.routeClass

@Composable
fun EditItemScreen(
    index: Int
) {
    val viewModel = hiltViewModel<EditItemViewModel, EditItemViewModel.Factory> { factory ->
        factory.create(index)
    }
    val navController = LocalNavController.current
    EventConsumer(channel = viewModel.exitChannel) {
        if (navController.currentBackStackEntry.routeClass() == RouteEditItem::class) {
            navController.popBackStack()
        }
    }
    val screenState by viewModel.stateFlow.collectAsStateWithLifecycle()
    EditItemContent(
        loadResult = screenState,
        onEditButtonClicked = viewModel::update,
    )
}

@Composable
fun EditItemContent(
    loadResult: LoadResult<EditItemViewModel.ScreenState>,
    onEditButtonClicked: (String) -> Unit,
) {

    LoadResultContent(
        loadResult = loadResult,
        content = { screenState ->
            SuccessEditItemContent(
                state = screenState,
                onEditButtonClicked = onEditButtonClicked
            )
        }
    )
}

@Composable
private fun SuccessEditItemContent(
    state: EditItemViewModel.ScreenState,
    onEditButtonClicked: (String) -> Unit,
) {

    ItemDetails(
        state = ItemDetailsState(
            loadedItem = state.loadedItem,
            textFieldPlaceholder = stringResource(id = R.string.edit_item_title),
            actionButtonText = stringResource(id = R.string.edit),
            isActionInProgress = state.isEditInProgress
        ),
        onActionButtonClicked = onEditButtonClicked,
        modifier = Modifier.fillMaxSize()
    )
}