package com.kbcoding.l54_dialogs.data

class LoadDataException : Exception()

class DuplicateException(
    val duplicatedValue: String,
) : Exception("The list can't contain duplicated items")