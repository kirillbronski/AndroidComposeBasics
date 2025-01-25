package com.kbcoding.l46_nav_component_hilt_environment.screens.addItem

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kbcoding.l46_nav_component_hilt_environment.EventConsumer
import com.kbcoding.l46_nav_component_hilt_environment.screens.LocalNavController
import com.kbcoding.l46_nav_component_hilt_environment.screens.Route

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
            if (navController.currentBackStackEntry?.destination?.route == Route.AddItem.path) {
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
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        var inputText by rememberSaveable {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = inputText,
            onValueChange = {
                inputText = it
            },
            placeholder = {
                Text(text = "Enter new item")
            },
            enabled = screenState.isTextInputEnabled,
        )
        Button(
            onClick = {
                onAddButtonClicked(inputText)
            },
            enabled = screenState.isAddButtonEnabled(inputText),
        ) {
            Text(text = "Add")
        }
        Box(modifier = Modifier.size(32.dp)) {
            if (screenState.isProgressVisible) {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            }
        }
    }
}