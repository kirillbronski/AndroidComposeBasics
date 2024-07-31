package com.kbcoding.l46_1_nav_component_hilt_environment.screens.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbcoding.l46_1_nav_component_hilt_environment.data.repository.ItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    repository: ItemsRepository
): ViewModel() {

    val stateFlow: StateFlow<ScreenState> = repository.getItems()
        .map(ScreenState::Success)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = ScreenState.Loading
        )

    sealed class ScreenState {
        data object Loading : ScreenState()
        data class Success(val items: List<String>) : ScreenState()
    }
}