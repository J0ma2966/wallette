package com.arshapshap.feature_statistics_impl.presentation.screen.transactionsList.groupsRecyclerView.transactionGroups

import com.arshapshap.common.domain.models.Category
import com.arshapshap.common.domain.models.Transaction

data class TransactionGroupByCategory(
    val category: Category?,
    override val list: List<Transaction>,
    override var isExpanded: Boolean = false
) : TransactionGroup