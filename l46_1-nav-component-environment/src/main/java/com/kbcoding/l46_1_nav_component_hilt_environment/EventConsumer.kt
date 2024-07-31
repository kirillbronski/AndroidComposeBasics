package com.kbcoding.l46_1_nav_component_hilt_environment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.withContext

@Composable
fun <T> EventConsumer(
    channel: ReceiveChannel<T>,
    block: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    val blockState by rememberUpdatedState(block)
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Main.immediate) {
            for (event in channel) blockState(event)
        }
    }
}