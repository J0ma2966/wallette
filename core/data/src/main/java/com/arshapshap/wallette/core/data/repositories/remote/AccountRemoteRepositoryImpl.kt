package com.arshapshap.wallette.core.data.repositories.remote

import com.arshapshap.wallette.core.common.domain.models.Account
import com.arshapshap.wallette.core.common.domain.models.network.BasicResult
import com.arshapshap.wallette.core.network.data.services.AccountsApiService
import com.arshapshap.wallette.core.data.managers.interfaces.AccountRemoteRepository
import com.arshapshap.wallette.core.data.mappers.AccountMapper
import com.arshapshap.wallette.core.data.mappers.BasicResultMapper
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