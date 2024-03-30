package com.arshapshap.wallette.feature.statistics.domain.models

import com.arshapshap.wallette.core.common.domain.models.Category
import com.arshapshap.wallette.core.common.domain.models.enums.TransactionType


data class TransactionShortInfo(
    val amount: Double,
    val category: Category?,
    val type: TransactionType
)
