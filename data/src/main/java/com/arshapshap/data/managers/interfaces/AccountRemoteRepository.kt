package com.arshapshap.data.managers.interfaces

import com.arshapshap.common.domain.models.Account
import com.arshapshap.common.domain.models.network.BasicResult

interface AccountRemoteRepository {

    suspend fun createAccountRemote(account: Account): BasicResult

    suspend fun updateAccountRemote(account: Account): BasicResult

    suspend fun deleteAccountRemote(account: Account): BasicResult
}