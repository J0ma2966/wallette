package com.arshapshap.data.managers.interfaces

import com.arshapshap.common.domain.models.Transaction
import com.arshapshap.common.domain.models.network.BasicResult

interface TransactionRemoteRepository {

    suspend fun createTransactionRemote(transaction: Transaction): BasicResult

    suspend fun updateTransactionRemote(transaction: Transaction): BasicResult

    suspend fun deleteTransactionRemote(transaction: Transaction): BasicResult
}