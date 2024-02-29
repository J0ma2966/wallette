package com.arshapshap.common.domain.models.enums

import androidx.annotation.StringRes
import com.arshapshap.common.R

enum class Language(@StringRes val stringRes: Int) {
    RU(R.string.language_ru),
    EN(R.string.language_en)
}