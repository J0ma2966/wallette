package com.arshapshap.wallette.core.common.domain.models.enums

import androidx.annotation.DrawableRes

interface Icon {

    @get:DrawableRes
    val drawableRes: Int
}