package com.arshapshap.wallette.feature.statistics.domain.models

import com.arshapshap.wallette.core.common.domain.models.Transaction

interface TransactionGroup {
    val list: List<Transaction>
    var isExpanded: Boolean
}