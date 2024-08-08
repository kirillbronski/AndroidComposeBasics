package com.kbcoding.l47_nav_component_hilt_args.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemsRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    private val itemsFlow =
        MutableStateFlow(List(size = 30) { "Item ${it + 1}" })

    suspend fun addItem(title: String) {
        delay(2000)
        itemsFlow.update { it + title }
    }

    fun getItems(): Flow<List<String>> {
        return itemsFlow.onStart { delay(3000) }
    }
}