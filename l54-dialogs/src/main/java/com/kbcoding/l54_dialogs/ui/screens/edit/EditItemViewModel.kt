package com.kbcoding.l54_dialogs.ui.screens.edit

import androidx.lifecycle.ViewModel
import com.kbcoding.l54_dialogs.data.repository.ItemsRepository
import com.kbcoding.l54_dialogs.ui.action.ActionViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = EditItemViewModel.Factory::class)
class EditItemViewModel @AssistedInject constructor(
    @Assisted private val index: Int,
    private val itemsRepository: ItemsRepository,
) : ViewModel(), ActionViewModel.Delegate<EditItemViewModel.ScreenState, String> {


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

    override fun hideProgress(input: ScreenState): ScreenState {
        return input.copy(isEditInProgress = false)
    }

    override suspend fun execute(action: String) {
        itemsRepository.update(index, action)
    }

    override fun showProgress(input: ScreenState): ScreenState {
        return input.copy(isEditInProgress = true)
    }

}