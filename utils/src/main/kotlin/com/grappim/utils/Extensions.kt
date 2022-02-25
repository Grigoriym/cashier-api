package com.grappim.utils

import java.util.*

fun String.toUUID(): UUID = UUID.fromString(this)

fun Int.padWithZeroes(length: Int): String = this.toString().padStart(length, '0')

fun String.padWithZeroes(length: Int): String = this.padStart(length, '0')