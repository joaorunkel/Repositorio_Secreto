package com.confradestech.waterly.utilites.extensions

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale
fun Float.formatFloatToTwoDecimals(): Float {
    val multipliedValue = this * 100
    val formattedString = "%.2f".format(multipliedValue)

    val sanitizedString = formattedString.replace(',', '.')

    return sanitizedString.toFloat()
}
