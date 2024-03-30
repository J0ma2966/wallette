package com.arshapshap.wallette.feature.statistics.domain.models

import com.arshapshap.wallette.core.common.domain.models.Category
import com.arshapshap.wallette.core.common.domain.models.Transaction

data class TransactionGroupByCategory(
    val category: Category?,
    override val list: List<Transaction>,
    override var isExpanded: Boolean = false
) : TransactionGroup