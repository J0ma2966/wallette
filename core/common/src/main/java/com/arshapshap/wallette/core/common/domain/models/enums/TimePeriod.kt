package com.arshapshap.wallette.core.common.domain.models.enums

import androidx.annotation.StringRes
import com.arshapshap.wallette.core.common.R

enum class TimePeriod(@StringRes val stringRes: Int) {
    Day(R.string.day),
    Week(R.string.week),
    Month(R.string.month),
    Year(R.string.year),
    All(R.string.all)
}