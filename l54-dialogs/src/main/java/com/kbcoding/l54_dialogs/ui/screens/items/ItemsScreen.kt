package com.kbcoding.l54_dialogs.ui.screens.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kbcoding.l54_dialogs.data.LoadResult
import com.kbcoding.l54_dialogs.ui.components.LoadResultContent
import com.kbcoding.l54_dialogs.ui.screens.ItemsGraph
import com.kbcoding.l54_dialogs.ui.screens.LocalNavController


@Composable
fun ItemsScreen() {

    val viewModel: ItemsViewModel = hiltViewModel()
    val uiState = viewModel.stateFlow.collectAsStateWithLifecycle()
    val navController = LocalNavController.current

    ItemsContent(
        getLoadResult = { uiState.value },
        onItemClicked = { index ->
            navController.navigate(ItemsGraph.RouteEditItem(index))
        }
    )
}

@Composable
fun ItemsContent(
    getLoadResult: () -> LoadResult<ItemsViewModel.ScreenState>,
    onItemClicked: (Int) -> Unit = {},
) {

    LoadResultContent(
        loadResult = getLoadResult.invoke(),
        content = { screenState ->
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
    )
}

@Preview(showSystemUi = true)
@Composable
fun ItemsScreenPreview() {
    ItemsContent(
        getLoadResult = { LoadResult.Success(ItemsViewModel.ScreenState(listOf("1", "2", "3"))) },
    )
}