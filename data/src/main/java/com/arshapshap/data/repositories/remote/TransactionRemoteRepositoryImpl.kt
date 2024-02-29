package com.arshapshap.data.repositories.remote

import com.arshapshap.common.domain.models.Transaction
import com.arshapshap.common.domain.models.network.BasicResult
import com.arshapshap.core_network.data.services.TransactionsApiService
import com.arshapshap.data.managers.interfaces.TransactionRemoteRepository
import com.arshapshap.data.mappers.BasicResultMapper
import com.arshapshap.data.mappers.TransactionMapper
import javax.inject.Inject

class TransactionRemoteRepositoryImpl @Inject constructor(
    private val remoteSource: TransactionsApiService,
    private val mapper: TransactionMapper,
    private val resultMapper: BasicResultMapper
) : TransactionRemoteRepository {

    override suspend fun createTransactionRemote(transaction: Transaction): BasicResult {
        val model = mapper.mapToCreatingModel(transaction)
        val response = remoteSource.createTransaction(model)
        return resultMapper.map(response)
    }

    override suspend fun updateTransactionRemote(transaction: Transaction): BasicResult {
        val model = mapper.mapToEditingModel(transaction)
        val response = remoteSource.updateTransaction(model)
        return resultMapper.map(response)
    }

    override suspend fun deleteTransactionRemote(transaction: Transaction): BasicResult {
        val response = remoteSource.deleteTransactionById(transaction.id)
        return resultMapper.map(response)
    }
}