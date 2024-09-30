package com.kbcoding.l53_event_and_state.ui.action

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kbcoding.l53_event_and_state.ui.components.ExceptionToMessageMapper
import com.kbcoding.l53_event_and_state.ui.components.LoadResultContent
import com.kbcoding.l53_event_and_state.ui.screens.LocalNavController

data class ActionContentState<State, Action>(
    val state: State,
    val onExecuteAction: (Action) -> Unit,
)

@Composable
fun <State, Action> ActionScreen(
    delegate: ActionViewModel.Delegate<State, Action>,
    content: @Composable (ActionContentState<State, Action>) -> Unit,
    modifier: Modifier = Modifier,
    exceptionToMessageMapper: ExceptionToMessageMapper = ExceptionToMessageMapper.Default,
) {
    val viewModel = viewModel<ActionViewModel<State, Action>> {
        ActionViewModel(delegate)
    }
    val navController = LocalNavController.current
    val baseScreenState by viewModel.baseScreenState.collectAsStateWithLifecycle(
        minActiveState = Lifecycle.State.RESUMED
    )

    LaunchedEffect(baseScreenState) {
        baseScreenState.exit?.let {
            navController.popBackStack()
            viewModel.handledExit()
        }
    }
//    EventConsumer(channel = viewModel.exitChannel) {
//        navController.popBackStack()
//    }
    val context = LocalContext.current
    LaunchedEffect(baseScreenState) {
        baseScreenState.exception?.let { exception ->
            val message = exceptionToMessageMapper.getUserMessage(exception, context)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            viewModel.onExceptionHandled()
        }
    }
//    EventConsumer(channel = viewModel.errorChannel) { exception ->
//        val message = exceptionToMessageMapper.getUserMessage(exception, context)
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//    }
    val loadResult by viewModel.stateFlow.collectAsStateWithLifecycle()
    LoadResultContent(
        loadResult = loadResult,
        content = { state ->
            val actionContentState = ActionContentState(
                state = state,
                onExecuteAction = viewModel::execute,
            )
            content(actionContentState)
        },
        modifier = modifier,
        onTryAgainAction = viewModel::load,
    )
}