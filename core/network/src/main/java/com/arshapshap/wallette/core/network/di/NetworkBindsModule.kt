package com.arshapshap.wallette.core.network.di

import com.arshapshap.wallette.core.common.data.TokenManager
import com.arshapshap.wallette.core.network.data.managers.TokenManagerImpl
import dagger.Binds
import dagger.Module

@Module
interface NetworkBindsModule {

    @Binds
    fun bindTokenManager(tokenManagerImpl: TokenManagerImpl): TokenManager
}