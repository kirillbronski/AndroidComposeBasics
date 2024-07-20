package com.kbcoding.l46_nav_component_hilt_environment.screens.addItem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbcoding.l46_nav_component_hilt_environment.data.repository.ItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val repository: ItemsRepository
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(ScreenState())
    val stateFlow = _stateFlow.asStateFlow()

    private val _exitChannel = Channel<Unit>()
    val exitChannel: ReceiveChannel<Unit> = _exitChannel

    fun addItem(title: String) {
        viewModelScope.launch {
            _stateFlow.update { it.copy(isAddInProgress = true) }
            repository.addItem(title = title)
            _exitChannel.send(Unit)
        }
    }

    data class ScreenState(
        private val isAddInProgress: Boolean = false
    ) {
        val isTextInputEnabled: Boolean get() = !isAddInProgress
        val isProgressVisible: Boolean get() = isAddInProgress

        fun isAddButtonEnabled(input: String): Boolean {
            return input.isNotBlank() && !isAddInProgress
        }
    }
}