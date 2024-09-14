package com.kbcoding.l52_errors.ui.screens.addItem

import androidx.lifecycle.ViewModel
import com.kbcoding.l52_errors.data.repository.ItemsRepository
import com.kbcoding.l52_errors.ui.action.ActionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val itemsRepository: ItemsRepository,
): ViewModel(), ActionViewModel.Delegate<AddItemViewModel.ScreenState, String> {

    override suspend fun loadState(): ScreenState {
        return ScreenState()
    }

    override fun showProgress(input: ScreenState): ScreenState {
        return input.copy(isProgressVisible = true)
    }

    override fun hideProgress(input: ScreenState): ScreenState {
        return input.copy(isProgressVisible = false)
    }

    override suspend fun execute(action: String) {
        itemsRepository.add(action)
    }

    data class ScreenState(
        val isProgressVisible: Boolean = false,
    )
}