package com.kbcoding.l41_nav_view_models.ui.screens.item

import androidx.lifecycle.ViewModel
import com.kbcoding.l41_nav_view_models.ItemsRepository

class ItemViewModel(
    private val args: ItemScreenArgs,
    private val repository: ItemsRepository = ItemsRepository.get(),
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