package com.kbcoding.navigationstack.navigation.internal

import com.kbcoding.navigationstack.navigation.ScreenResponseReceiver
import kotlin.reflect.KClass

internal object EmptyScreenResponseReceiver : ScreenResponseReceiver {
    override fun <T : Any> get(clazz: KClass<T>): T? = null
}