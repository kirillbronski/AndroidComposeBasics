package com.kbcoding.l48_nav_component_refactoring.screens.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kbcoding.l48_nav_component_refactoring.screens.RouteEditItem
import com.kbcoding.l48_nav_component_refactoring.screens.LocalNavController


@Composable
fun ItemsScreen() {

    val viewModel: ItemsViewModel = hiltViewModel()
    val uiState = viewModel.stateFlow.collectAsStateWithLifecycle()
    val navController = LocalNavController.current

    ItemsContent(
        getScreenState = { uiState.value },
        onItemClicked = { index ->
            navController.navigate(RouteEditItem(index))
        }
    )
}

@Composable
fun ItemsContent(
    getScreenState: () -> ItemsViewModel.ScreenState,
    onItemClicked: (Int) -> Unit = {},
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
                    itemsIndexed(screenState.items) { index, item ->
                        Text(
                            text = item,
                            modifier = Modifier
                                .clickable { onItemClicked(index) }
                                .fillMaxWidth()
                                .padding(12.dp)
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