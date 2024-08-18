package com.kbcoding.l49_nav_component_refactoring2.ui.screens.addItem

import androidx.lifecycle.ViewModel
import com.kbcoding.l49_nav_component_refactoring2.data.repository.ItemsRepository
import com.kbcoding.l49_nav_component_refactoring2.ui.action.ActionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val repository: ItemsRepository
) : ViewModel(), ActionViewModel.Delegate<AddItemViewModel.ScreenState, String> {

//    private val _stateFlow = MutableStateFlow(ScreenState())
//    val stateFlow = _stateFlow.asStateFlow()
//
//    private val _exitChannel = Channel<Unit>()
//    val exitChannel: ReceiveChannel<Unit> = _exitChannel
//
//    fun addItem(title: String) {
//        viewModelScope.launch {
//            _stateFlow.update { it.copy(isAddInProgress = true) }
//            repository.add(title = title)
//            _exitChannel.send(Unit)
//        }
//    }

    data class ScreenState(
        val isProgressVisible: Boolean = false
    )

    override suspend fun loadState(): ScreenState {
        return ScreenState()
    }

    override suspend fun execute(action: String) {
        repository.add(title = action)
    }

    override fun showProgress(input: ScreenState): ScreenState {
        return input.copy(isProgressVisible = true)
    }
}