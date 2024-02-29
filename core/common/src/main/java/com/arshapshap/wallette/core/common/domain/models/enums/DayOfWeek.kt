package com.arshapshap.wallette.core.common.domain.models.enums

import androidx.annotation.StringRes
import com.arshapshap.wallette.core.common.R

enum class DayOfWeek(@StringRes val stringRes: Int) {
    Monday(R.string.monday),
    Tuesday(R.string.tuesday),
    Wednesday(R.string.wednesday),
    Thursday(R.string.thursday),
    Friday(R.string.friday),
    Saturday(R.string.saturday),
    Sunday(R.string.sunday)
}