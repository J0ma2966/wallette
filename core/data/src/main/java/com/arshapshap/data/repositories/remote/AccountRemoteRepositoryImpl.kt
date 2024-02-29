package com.arshapshap.data.repositories.remote

import com.arshapshap.common.domain.models.Account
import com.arshapshap.common.domain.models.network.BasicResult
import com.arshapshap.core_network.data.services.AccountsApiService
import com.arshapshap.data.managers.interfaces.AccountRemoteRepository
import com.arshapshap.data.mappers.AccountMapper
import com.arshapshap.data.mappers.BasicResultMapper
import javax.inject.Inject

class AccountRemoteRepositoryImpl @Inject constructor(
    private val remoteSource: AccountsApiService,
    private val mapper: AccountMapper,
    private val resultMapper: BasicResultMapper
): AccountRemoteRepository {

    override suspend fun createAccountRemote(account: Account): BasicResult {
        val model = mapper.mapToCreatingModel(account)
        val response = remoteSource.createAccount(model)
        return resultMapper.map(response)
    }

    override suspend fun updateAccountRemote(account: Account): BasicResult {
        val model = mapper.mapToEditingModel(account)
        val response = remoteSource.updateAccount(model)
        return resultMapper.map(response)
    }

    override suspend fun deleteAccountRemote(account: Account): BasicResult {
        val response = remoteSource.deleteAccountById(account.id)
        return resultMapper.map(response)
    }
}