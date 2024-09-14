package com.kbcoding.l52_errors.ui.screens.addItem

import androidx.lifecycle.ViewModel
import com.kbcoding.l52_errors.data.repository.ItemsRepository
import com.kbcoding.l52_errors.ui.action.ActionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val repository: ItemsRepository
) : ViewModel(), ActionViewModel.Delegate<AddItemViewModel.ScreenState, String> {

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