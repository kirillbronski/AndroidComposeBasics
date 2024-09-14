package com.kbcoding.l52_errors.ui.screens.addItem

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.kbcoding.l52_errors.R
import com.kbcoding.l52_errors.ui.screens.addItem.AddItemViewModel
import com.kbcoding.l52_errors.ui.action.ActionScreen
import com.kbcoding.l52_errors.ui.components.ItemDetails
import com.kbcoding.l52_errors.ui.components.ItemDetailsState

@Composable
fun AddItemScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<AddItemViewModel>()

    ActionScreen(
        delegate = viewModel,
        content = { (screenState, executeAction) ->
            AddItemContent(
                screenState = screenState,
                onAddButtonClicked = executeAction
            )
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