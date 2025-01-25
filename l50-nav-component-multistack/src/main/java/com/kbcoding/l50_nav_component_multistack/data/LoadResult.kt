package com.kbcoding.l50_nav_component_multistack.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

sealed class LoadResult<out T> {
    data object Loading : LoadResult<Nothing>()
    data class Success<T>(val data: T) : LoadResult<T>()
    data class Error(val message: String) : LoadResult<Nothing>()
}

inline fun <Input, Output> LoadResult<Input>.map(
    mapper: (Input) -> Output,
): LoadResult<Output> {
    return when (this) {
        LoadResult.Loading -> LoadResult.Loading
        is LoadResult.Success -> LoadResult.Success(mapper(data))
        is LoadResult.Error -> LoadResult.Error(message)
    }
}

inline fun <T> MutableStateFlow<LoadResult<T>>.tryUpdate(
    updater: (T) -> T,
) {
    this.update { loadResult -> loadResult.map(updater) }
}