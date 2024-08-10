package com.kbcoding.l48_nav_component_refactoring.ui.screens.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kbcoding.l48_nav_component_refactoring.R
import com.kbcoding.l48_nav_component_refactoring.EventConsumer
import com.kbcoding.l48_nav_component_refactoring.data.LoadResult
import com.kbcoding.l48_nav_component_refactoring.ui.components.ItemDetails
import com.kbcoding.l48_nav_component_refactoring.ui.components.ItemDetailsState
import com.kbcoding.l48_nav_component_refactoring.ui.components.LoadResultContent
import com.kbcoding.l48_nav_component_refactoring.ui.screens.RouteEditItem
import com.kbcoding.l48_nav_component_refactoring.ui.screens.LocalNavController
import com.kbcoding.l48_nav_component_refactoring.ui.screens.routeClass

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