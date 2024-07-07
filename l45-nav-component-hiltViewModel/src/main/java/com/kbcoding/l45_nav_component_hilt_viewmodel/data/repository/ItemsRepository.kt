package com.kbcoding.l45_nav_component_hilt_viewmodel.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemsRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
}