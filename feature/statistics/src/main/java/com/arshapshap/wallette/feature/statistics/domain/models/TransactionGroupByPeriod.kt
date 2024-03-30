package com.arshapshap.wallette.feature.statistics.domain.models

import java.util.*

data class TransactionGroupByPeriod(
    val periodStart: Date? = null,
    val periodEnd: Date? = null,
    val list: List<TransactionShortInfo>,
    val income: Double,
    val expense: Double
)