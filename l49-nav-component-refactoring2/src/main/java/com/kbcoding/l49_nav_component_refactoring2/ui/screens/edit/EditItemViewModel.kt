package com.kbcoding.l49_nav_component_refactoring2.ui.screens.edit

import androidx.lifecycle.ViewModel
import com.kbcoding.l49_nav_component_refactoring2.data.repository.ItemsRepository
import com.kbcoding.l49_nav_component_refactoring2.ui.action.ActionViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = EditItemViewModel.Factory::class)
class EditItemViewModel @AssistedInject constructor(
    @Assisted private val index: Int,
    private val itemsRepository: ItemsRepository,
) : ViewModel(), ActionViewModel.Delegate<EditItemViewModel.ScreenState, String> {

//    private val _stateFlow = MutableStateFlow<LoadResult<ScreenState>>(LoadResult.Loading)
//    val stateFlow: StateFlow<LoadResult<ScreenState>> = _stateFlow
//
//    private val _exitChannel = Channel<Unit>()
//    val exitChannel: ReceiveChannel<Unit> = _exitChannel
//
//    init {
//        viewModelScope.launch {
//            val loadedItem = itemsRepository.getByIndex(index)
//            _stateFlow.value = LoadResult.Success(ScreenState(loadedItem))
//        }
//    }
//
//    fun update(newValue: String) {
//        val loadResult = _stateFlow.value
//        if (loadResult !is LoadResult.Success) return
//        viewModelScope.launch {
//            showProgress(loadResult)
//            itemsRepository.update(index, newValue)
//            goBack()
//        }
//    }
//
//    private suspend fun goBack() {
//        _exitChannel.send(Unit)
//    }
//
//    private fun showProgress(loadResult: LoadResult.Success<ScreenState>) {
//        val currentScreenState = loadResult.data
//        val updatedScreenState = currentScreenState.copy(isEditInProgress = true)
//        _stateFlow.value = LoadResult.Success(updatedScreenState)
//    }


    data class ScreenState(
        val loadedItem: String,
        val isEditInProgress: Boolean = false,
    )

    @AssistedFactory
    interface Factory {
        fun create(index: Int): EditItemViewModel
    }

    override suspend fun loadState(): ScreenState {
        return ScreenState(loadedItem = itemsRepository.getByIndex(index))
    }

    override suspend fun execute(action: String) {
        itemsRepository.update(index, action)
    }

    override fun showProgress(input: ScreenState): ScreenState {
        return input.copy(isEditInProgress = true)
    }

}