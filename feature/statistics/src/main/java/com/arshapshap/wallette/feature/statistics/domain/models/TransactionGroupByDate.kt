package com.arshapshap.wallette.feature.statistics.domain.models

import com.arshapshap.wallette.core.common.domain.models.Transaction
import java.util.*

data class TransactionGroupByDate(
    val date: Date,
    override val list: List<Transaction>,
    override var isExpanded: Boolean = true
) : TransactionGroup

