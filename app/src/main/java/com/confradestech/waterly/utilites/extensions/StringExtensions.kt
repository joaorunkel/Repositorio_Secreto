package com.confradestech.waterly.utilites.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.toDate(): Date? {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return try {
        dateFormat.parse(this)
    } catch (e: Exception) {
        null
    }
}