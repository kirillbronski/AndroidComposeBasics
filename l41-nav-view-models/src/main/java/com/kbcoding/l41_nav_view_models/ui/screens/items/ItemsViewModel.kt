package com.kbcoding.l41_nav_view_models.ui.screens.items

import androidx.lifecycle.ViewModel
import com.kbcoding.l41_nav_view_models.ItemsRepository
import com.kbcoding.l41_nav_view_models.ui.screens.item.ItemScreenArgs
import com.kbcoding.l41_nav_view_models.ui.screens.item.ItemScreenResponse

class ItemsViewModel(
    private val repository: ItemsRepository = ItemsRepository.get(),
) : ViewModel() {

    val itemsFlow = repository.getItems()

    fun processResponse(response: ItemScreenResponse) {
        when (response.args) {
            is ItemScreenArgs.Add ->
                repository.addItem(response.newValue)

            is ItemScreenArgs.Edit ->
                repository.updateItem(response.args.index, response.newValue)
        }
    }
}