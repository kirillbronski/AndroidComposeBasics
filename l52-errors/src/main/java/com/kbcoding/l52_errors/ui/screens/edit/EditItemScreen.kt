package com.kbcoding.l52_errors.ui.screens.edit

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.kbcoding.l52_errors.R
import com.kbcoding.l52_errors.ui.screens.edit.EditItemViewModel
import com.kbcoding.l52_errors.ui.action.ActionScreen
import com.kbcoding.l52_errors.ui.components.ItemDetails
import com.kbcoding.l52_errors.ui.components.ItemDetailsState

@Composable
fun EditItemScreen(
    index: Int
) {
    val viewModel = hiltViewModel<EditItemViewModel, EditItemViewModel.Factory> { factory ->
        factory.create(index)
    }

    ActionScreen(
        delegate = viewModel,
        content = { (screenState, onExecuteAction) ->
            EditItemContent(state = screenState, onEditButtonClicked = onExecuteAction)
        }
    )
}

@Composable
private fun EditItemContent(
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