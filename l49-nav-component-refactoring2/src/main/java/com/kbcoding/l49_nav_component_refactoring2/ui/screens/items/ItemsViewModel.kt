package com.kbcoding.l49_nav_component_refactoring2.ui.screens.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbcoding.l49_nav_component_refactoring2.data.LoadResult
import com.kbcoding.l49_nav_component_refactoring2.data.repository.ItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    repository: ItemsRepository
) : ViewModel() {

    val stateFlow: StateFlow<LoadResult<ScreenState>> = repository.getItems()
        .map { LoadResult.Success(ScreenState(it)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = LoadResult.Loading
        )

    data class ScreenState(val items: List<String>)
}