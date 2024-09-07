package com.kbcoding.l51_nav_component_deep_links.ui.screens.edit

import androidx.lifecycle.ViewModel
import com.kbcoding.l51_nav_component_deep_links.data.repository.ItemsRepository
import com.kbcoding.l51_nav_component_deep_links.ui.action.ActionViewModel
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

    override suspend fun execute(action: String) {
        itemsRepository.update(index, action)
    }

    override fun showProgress(input: ScreenState): ScreenState {
        return input.copy(isEditInProgress = true)
    }

}