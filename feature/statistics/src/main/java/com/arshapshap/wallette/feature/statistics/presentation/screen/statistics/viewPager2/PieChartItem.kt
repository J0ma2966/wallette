package com.arshapshap.wallette.feature.statistics.presentation.screen.statistics.viewPager2

import androidx.annotation.ColorInt

data class PieChartItem(
    val amount: Float,
    val name: String,
    @ColorInt val color: Int
)
