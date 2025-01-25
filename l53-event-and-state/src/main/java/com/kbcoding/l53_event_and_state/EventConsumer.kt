package com.kbcoding.l53_event_and_state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.withContext

@Composable
fun <T> EventConsumer(channel: ReceiveChannel<T>, block: (T) -> Unit) {
    val blockState by rememberUpdatedState(block)
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Main.immediate) {
            for (event in channel) blockState(event)
        }
    }
}