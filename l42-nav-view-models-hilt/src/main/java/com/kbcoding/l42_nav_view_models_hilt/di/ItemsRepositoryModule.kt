package com.kbcoding.l42_nav_view_models_hilt.di

import com.kbcoding.l42_nav_view_models_hilt.ItemsRepository
import com.kbcoding.l42_nav_view_models_hilt.ItemsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ItemsRepositoryModule {

    @Binds
    fun bindItemsRepository(impl: ItemsRepositoryImpl): ItemsRepository

}