package com.kbcoding.l46_1_nav_component_hilt_environment.screens.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kbcoding.l46_1_nav_component_environment.R
import com.kbcoding.l46_1_nav_component_hilt_environment.navigation.CustomScreenBuilder
import com.kbcoding.l46_1_nav_component_hilt_environment.navigation.FloatingAction
import com.kbcoding.l46_1_nav_component_hilt_environment.screens.Route


fun CustomScreenBuilder.itemsScreen() {
    environment {
        titleRes = R.string.items_screen
        floatingAction = FloatingAction(
            icon = Icons.Default.Add,
            onClick = {
                navController.navigate(Route.AddItem.path)
            }
        )
    }

    content {
        val viewModel: ItemsViewModel = hiltViewModel()
        val uiState = viewModel.stateFlow.collectAsState()

        ItemsContent(
            getScreenState = { uiState.value },
        )
    }


}

@Composable
fun ItemsContent(
    getScreenState: () -> ItemsViewModel.ScreenState,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        when (val screenState = getScreenState()) {
            is ItemsViewModel.ScreenState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is ItemsViewModel.ScreenState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(screenState.items) {
                        Text(
                            text = it,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ItemsScreenPreview() {
    ItemsContent(
        getScreenState = { ItemsViewModel.ScreenState.Loading },
    )
}