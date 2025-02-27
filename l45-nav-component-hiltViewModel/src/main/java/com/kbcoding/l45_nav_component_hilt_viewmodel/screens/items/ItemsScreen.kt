package com.kbcoding.l45_nav_component_hilt_viewmodel.screens.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kbcoding.l45_nav_component_hilt_viewmodel.screens.LocalNavController
import com.kbcoding.l45_nav_component_hilt_viewmodel.screens.Route

@Composable
fun ItemsScreen() {

    val viewModel: ItemsViewModel = hiltViewModel()
    val navController = LocalNavController.current
    val uiState = viewModel.stateFlow.collectAsState()

    ItemsContent(
        getScreenState = { uiState.value },
        onLaunchAddItemScreen = {
            navController.navigate(Route.AddItem.path)
        },
    )
}

@Composable
fun ItemsContent(
    getScreenState: () -> ItemsViewModel.ScreenState,
    onLaunchAddItemScreen: () -> Unit,
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
        FloatingActionButton(
            onClick = onLaunchAddItemScreen,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ItemsScreenPreview() {
    ItemsContent(
        getScreenState = { ItemsViewModel.ScreenState.Loading },
        onLaunchAddItemScreen = {}
    )
}