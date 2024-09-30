package com.kbcoding.l53_event_and_state.ui.action

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbcoding.l53_event_and_state.data.LoadResult
import com.kbcoding.l53_event_and_state.data.tryUpdate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ActionViewModel<State, Action>(
    private val delegate: Delegate<State, Action>,
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<LoadResult<State>>(LoadResult.Loading)
    val stateFlow: StateFlow<LoadResult<State>> = _stateFlow

    private val _baseScreenState = MutableStateFlow(BaseScreenState())
    val baseScreenState: StateFlow<BaseScreenState> = _baseScreenState

//    private val _exitChannel = Channel<Unit>()
//    val exitChannel: ReceiveChannel<Unit> = _exitChannel
//
//    private val _errorChannel = Channel<Exception>()
//    val errorChannel: ReceiveChannel<Exception> = _errorChannel

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _stateFlow.value = LoadResult.Loading
            _stateFlow.value = try {
                LoadResult.Success(delegate.loadState())
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }

    fun execute(action: Action) {
        viewModelScope.launch {
            showProgress()
            try {
                delegate.execute(action)
                goBack()
            } catch (e: Exception) {
                hideProgress()
                //_errorChannel.send(e)
                _baseScreenState.update { oldState ->
                    oldState.copy(exception = e)
                }
            }
        }
    }

    fun onExceptionHandled() {
        _baseScreenState.update { oldState ->
            oldState.copy(exception = null)
        }
    }

    fun handledExit() {
        _baseScreenState.update { oldState ->
            oldState.copy(exit = null)
        }
    }

    private fun showProgress() {
        _stateFlow.tryUpdate(delegate::showProgress)
    }

    private fun hideProgress() {
        _stateFlow.tryUpdate(delegate::hideProgress)
    }

    private fun goBack() {
        //_exitChannel.send(Unit)
        _baseScreenState.update { oldState ->
            oldState.copy(exit = Unit)
        }
    }

    interface Delegate<State, Action> {
        suspend fun loadState(): State
        fun showProgress(input: State): State
        fun hideProgress(input: State): State
        suspend fun execute(action: Action)
    }

    data class BaseScreenState(
        val exit: Unit? = null,
        val exception: Exception? = null,
    )
}
