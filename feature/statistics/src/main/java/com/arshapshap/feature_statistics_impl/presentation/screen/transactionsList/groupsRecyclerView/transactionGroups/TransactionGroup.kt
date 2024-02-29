package com.arshapshap.feature_statistics_impl.presentation.screen.transactionsList.groupsRecyclerView.transactionGroups

import com.arshapshap.common.domain.models.Transaction

interface TransactionGroup {
    val list: List<Transaction>
    var isExpanded: Boolean
}