package com.grappim.utils

import java.util.*

fun String.toUUID(): UUID = UUID.fromString(this)