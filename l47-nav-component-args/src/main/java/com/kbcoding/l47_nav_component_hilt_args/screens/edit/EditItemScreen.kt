package com.kbcoding.l47_nav_component_hilt_args.screens.edit

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
import com.kbcoding.l47_nav_component_args.R
import com.kbcoding.l47_nav_component_hilt_args.EventConsumer
import com.kbcoding.l47_nav_component_hilt_args.screens.LocalNavController
import com.kbcoding.l47_nav_component_hilt_args.screens.RouteEditItem
import com.kbcoding.l47_nav_component_hilt_args.screens.routeClass

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
        state = screenState,
        onEditButtonClicked = viewModel::update,
    )
}

@Composable
fun EditItemContent(
    state: EditItemViewModel.ScreenState,
    onEditButtonClicked: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (state) {
            EditItemViewModel.ScreenState.Loading -> CircularProgressIndicator()
            is EditItemViewModel.ScreenState.Success -> SuccessEditItemContent(
                state,
                onEditButtonClicked
            )
        }
    }
}

@Composable
private fun SuccessEditItemContent(
    state: EditItemViewModel.ScreenState.Success,
    onEditButtonClicked: (String) -> Unit,
) {
    var inputText by rememberSaveable { mutableStateOf(state.loadedItem) }
    OutlinedTextField(
        value = inputText,
        onValueChange = { inputText = it },
        placeholder = { Text(stringResource(R.string.edit_item_screen)) },
        enabled = !state.isEditInProgress,
    )
    Button(
        onClick = { onEditButtonClicked(inputText) },
        enabled = inputText.isNotBlank() && !state.isEditInProgress,
    ) {
        Text(text = stringResource(R.string.edit))
    }
    Box(modifier = Modifier.size(32.dp)) {
        if (state.isEditInProgress) {
            CircularProgressIndicator(Modifier.fillMaxSize())
        }
    }
}