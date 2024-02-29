package com.arshapshap.wallette.core.common.presentation.extensions

import com.arshapshap.wallette.core.common.domain.models.enums.Currency
import java.text.DecimalFormat
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.roundToInt
import com.arshapshap.wallette.core.common.R

fun Double.formatAsBalance(currency: Currency, withPlus: Boolean = true): String {
    val sign = if (this >= 0 && withPlus) "+" else if (this >= 0) "" else "-"
    val balance = DecimalFormat("#,##0.00").format(abs(this))
    return "$sign $balance ${currency.symbol}"
}

fun Double.getColorBySign()
    = if (this >= 0) R.color.green
    else R.color.red

fun Double.round(decimalPlaces: Int): Double {
    return (this * 10.0.pow(decimalPlaces)).roundToInt().toDouble() / 10.0.pow(decimalPlaces)
}