package com.kbcoding.l52_errors.data

class LoadDataException : Exception()

class DuplicateException(
    val duplicatedValue: String,
) : Exception("The list can't contain duplicated items")