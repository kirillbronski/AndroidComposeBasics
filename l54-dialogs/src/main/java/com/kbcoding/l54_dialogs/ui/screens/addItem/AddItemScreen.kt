package com.kbcoding.l54_dialogs.ui.screens.addItem

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kbcoding.l54_dialogs.R
import com.kbcoding.l54_dialogs.ui.action.ActionScreen
import com.kbcoding.l54_dialogs.ui.components.ItemDetails
import com.kbcoding.l54_dialogs.ui.components.ItemDetailsState

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

    Surface(
        color = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 16.dp,
        shadowElevation = 16.dp
    ) {
        ItemDetails(
            state = ItemDetailsState(
                loadedItem = "",
                textFieldPlaceholder = stringResource(id = R.string.enter_new_item),
                actionButtonText = stringResource(id = R.string.add),
                isActionInProgress = screenState.isProgressVisible
            ),
            onActionButtonClicked = onAddButtonClicked,
            modifier = Modifier.padding(16.dp)
        )
    }

}