package com.arshapshap.wallette.feature.statistics.presentation.screen.singleTransaction

import com.arshapshap.wallette.core.common.domain.models.*
import com.arshapshap.wallette.core.common.domain.models.enums.TransactionType
import java.util.*

data class EditingTransaction(
    val id: Long = 0,
    val type: TransactionType,
    val date: Date = Calendar.getInstance().time,
    val amount: Double? = null,
    val description: String = "",
    val account: Account? = null,
    val accountDestination: Account? = null,
    val category: Category? = null,
    val transactionGroup: Transaction? = null,
    val isTransactionGroup: Boolean? = null,
    val tags: List<Tag> = listOf()
)