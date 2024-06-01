package com.kbcoding.l42_nav_view_models_hilt.ui.screens.item

import androidx.lifecycle.ViewModel
import com.kbcoding.l42_nav_view_models_hilt.ItemsRepository

class ItemViewModel(
    private val args: ItemScreenArgs,
    private val repository: com.kbcoding.l42_nav_view_models_hilt.ItemsRepository = com.kbcoding.l42_nav_view_models_hilt.ItemsRepository.get(),
) : ViewModel() {

    init {
        println("AAAAA ItemViewModel-${hashCode()} created")
    }

    fun getInitialValue(): String {
        return when (args) {
            is ItemScreenArgs.Add -> ""
            is ItemScreenArgs.Edit -> repository.getItems().value[args.index]
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("AAAAA ItemViewModel-${hashCode()} destroyed")
    }

}