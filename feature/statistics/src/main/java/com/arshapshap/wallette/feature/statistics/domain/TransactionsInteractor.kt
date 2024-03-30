package com.arshapshap.wallette.feature.statistics.domain

import com.arshapshap.wallette.feature.statistics.presentation.screen.transactionsList.SortingType
import com.arshapshap.wallette.core.common.data.SettingsManager
import com.arshapshap.wallette.core.common.domain.models.Account
import com.arshapshap.wallette.core.common.domain.repositories.AccountRepository
import com.arshapshap.wallette.core.common.domain.repositories.TransactionRepository
import com.arshapshap.wallette.core.common.presentation.extensions.between
import com.arshapshap.wallette.feature.statistics.presentation.screen.transactionsList.groupsRecyclerView.transactionGroups.TransactionGroup
import com.arshapshap.wallette.feature.statistics.domain.utils.filterByViewedAccount
import com.arshapshap.wallette.feature.statistics.domain.utils.groupByCategory
import com.arshapshap.wallette.feature.statistics.domain.utils.groupByDate
import com.arshapshap.wallette.feature.statistics.domain.utils.groupByTag
import java.util.*
import javax.inject.Inject

class TransactionsInteractor @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository,
    private val settingsManager: SettingsManager
) {

    suspend fun getTransactionGroups(sortingType: SortingType, periodStart: Date?, periodEnd: Date?): List<TransactionGroup> {
        val transactions = transactionRepository.getTransactions()
            .filterByViewedAccount(getViewedAccount()?.id)
            .filter { it.date.between(periodStart, periodEnd) }

        val groups = when (sortingType) {
            SortingType.ByDate -> transactions.groupByDate()
            SortingType.ByCategory -> transactions.groupByCategory()
            SortingType.ByTag -> transactions.groupByTag()
        }
        return groups
    }

    private suspend fun getViewedAccount(): Account? {
        val accountId = settingsManager.getViewedAccountId() ?: return null

        val account = accountRepository.getAccountById(accountId)
        if (account == null)
            settingsManager.setViewedAccount(null)

        return account
    }
}