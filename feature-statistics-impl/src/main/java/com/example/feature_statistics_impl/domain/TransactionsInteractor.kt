package com.example.feature_statistics_impl.domain

import com.example.common.data.SettingsManager
import com.example.common.domain.repositories.TransactionRepository
import com.example.feature_statistics_impl.domain.models.TransactionGroup
import com.example.feature_statistics_impl.domain.utils.filterByViewedAccount
import com.example.feature_statistics_impl.domain.utils.groupByCategory
import com.example.feature_statistics_impl.domain.utils.groupByDate
import com.example.feature_statistics_impl.domain.utils.groupByTag
import com.example.feature_statistics_impl.presentation.screen.transactionsList.SortingType
import javax.inject.Inject

class TransactionsInteractor @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val settingsManager: SettingsManager
) {

    suspend fun getTransactionGroups(sortingType: SortingType): List<TransactionGroup> {
        val transactions = transactionRepository.getTransactions()
            .filterByViewedAccount(settingsManager.getViewedAccountId())

        val groups = when (sortingType) {
            SortingType.ByDate -> transactions.groupByDate()
            SortingType.ByCategory -> transactions.groupByCategory()
            SortingType.ByTag -> transactions.groupByTag()
        }
        return groups
    }
}