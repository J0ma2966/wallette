package com.arshapshap.wallette.core.common.presentation.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.between(start: Date?, end: Date?): Boolean {
    return this.roundToDay() == start?.roundToDay() || this.roundToDay() == end?.roundToDay()
            || (start == null && end == null)
            || (this.after(start) && end == null)
            || (start == null && this.before(end))
            || (this.after(start) && this.before(end))
}

fun Date.formatDayToString(): String {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    val calendar = Calendar.getInstance()
    calendar.time = this
    val year = calendar.get(Calendar.YEAR)

    val outputDateFormatter =
        if (currentYear == year)
            SimpleDateFormat("dd MMM", Locale.getDefault())
        else
            SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())

    return outputDateFormatter.format(this)
}

fun Date.formatMonthToString(): String {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    val calendar = Calendar.getInstance()
    calendar.time = this
    val year = calendar.get(Calendar.YEAR)

    val outputDateFormatter =
        if (currentYear == year)
            SimpleDateFormat("LLLL", Locale.getDefault())
        else
            SimpleDateFormat("LLLL, yyyy", Locale.getDefault())

    return outputDateFormatter.format(this).replaceFirstChar(Char::titlecase)
}

fun Date.formatYearToString(): String {
    val outputDateFormatter = SimpleDateFormat("yyyy", Locale.getDefault())

    return outputDateFormatter.format(this)
}

fun Date.roundToDay(): Date {
    val calendar = Calendar.getInstance().apply {
        time = this@roundToDay
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    return calendar.time
}