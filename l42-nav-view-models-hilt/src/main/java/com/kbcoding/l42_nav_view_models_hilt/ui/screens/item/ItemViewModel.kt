package com.kbcoding.l42_nav_view_models_hilt.ui.screens.item

import androidx.lifecycle.ViewModel
import com.kbcoding.l42_nav_view_models_hilt.ItemsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = ItemViewModel.Factory::class)
class ItemViewModel @AssistedInject constructor(
    @Assisted private val args: ItemScreenArgs,
    private val repository: ItemsRepository,
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

    @AssistedFactory
    interface Factory {
        fun create(args: ItemScreenArgs): ItemViewModel
    }

}