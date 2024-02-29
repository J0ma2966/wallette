package com.arshapshap.wallette.feature.statistics.presentation.screen.transactionsList.groupsRecyclerView.transactionGroups

import com.arshapshap.wallette.core.common.domain.models.Tag
import com.arshapshap.wallette.core.common.domain.models.Transaction

data class TransactionGroupByTag(
    val tag: Tag?,
    override val list: List<Transaction>,
    override var isExpanded: Boolean = false
) : TransactionGroup