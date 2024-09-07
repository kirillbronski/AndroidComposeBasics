package com.kbcoding.l50_nav_component_multistack.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kbcoding.l50_nav_component_multistack.data.LoadResult

@Composable
fun <T> LoadResultContent(
    loadResult: LoadResult<T>,
    content: @Composable (T) -> Unit,
    modifier: Modifier = Modifier
) {

    when (loadResult) {
        LoadResult.Loading -> Box(modifier = modifier) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        is LoadResult.Success -> content(loadResult.data)

        is LoadResult.Error -> TODO()
    }
}