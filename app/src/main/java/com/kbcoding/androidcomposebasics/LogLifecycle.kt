package com.kbcoding.androidcomposebasics

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.remember

private const val TAG = "CompositionLifecycle"

@Composable
fun logCompositionLifecycle(name: String): Any = remember {
    LifeCycleRememberObserver(name)
}

class LifeCycleRememberObserver(
    private val name: String
) : RememberObserver {

    override fun onAbandoned() = Unit

    override fun onForgotten() {
        Log.d(TAG, "$name.onLeave")
    }

    override fun onRemembered() {
        Log.d(TAG, "$name.onEnter")
    }
}
