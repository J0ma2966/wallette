package com.arshapshap.wallette.feature.statistics.presentation.screen.transactionsList.groupsRecyclerView.transactionGroups

import com.arshapshap.wallette.core.common.domain.models.Category
import com.arshapshap.wallette.core.common.domain.models.Transaction

data class TransactionGroupByCategory(
    val category: Category?,
    override val list: List<Transaction>,
    override var isExpanded: Boolean = false
) : TransactionGroup