package com.kbcoding.androidcomposebasics

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CounterState(
    val count: Int = 0
): Parcelable
