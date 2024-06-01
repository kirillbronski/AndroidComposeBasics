package com.kbcoding.l42_nav_view_models_hilt

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * Repository for storing and managing the list of items.
 */
interface ItemsRepository {

    /**
     * Get the list of items and listen for all further changes.
     */
    fun getItems(): StateFlow<List<String>>

    /**
     * Add a new item to the list. The flow returned by [getItems] will
     * be automatically updated.
     */
    fun addItem(item: String)

    fun updateItem(index: Int, newValues: String)

    /**
     * Remove all items from the list. The flow returned by [getItems] will
     * be automatically updated.
     */
    fun clear()

    companion object {
        fun get(): com.kbcoding.l42_nav_view_models_hilt.ItemsRepository =
            com.kbcoding.l42_nav_view_models_hilt.ItemsRepositoryImpl
    }
}

object ItemsRepositoryImpl : com.kbcoding.l42_nav_view_models_hilt.ItemsRepository {

    private val items = MutableStateFlow(com.kbcoding.l42_nav_view_models_hilt.ItemsRepositoryImpl.generateFakeItems())

    override fun getItems(): StateFlow<List<String>> {
        return com.kbcoding.l42_nav_view_models_hilt.ItemsRepositoryImpl.items
    }

    override fun addItem(item: String) {
        com.kbcoding.l42_nav_view_models_hilt.ItemsRepositoryImpl.items.update { it + item }
    }

    override fun updateItem(index: Int, newValues: String) {
        com.kbcoding.l42_nav_view_models_hilt.ItemsRepositoryImpl.items.update {
            it.toMutableList().apply {
                set(index, newValues)
            }
        }
    }

    override fun clear() {
        com.kbcoding.l42_nav_view_models_hilt.ItemsRepositoryImpl.items.update { emptyList() }
    }

    private fun generateFakeItems() = List(10) {
        "Item #${it + 1}"
    }
}
