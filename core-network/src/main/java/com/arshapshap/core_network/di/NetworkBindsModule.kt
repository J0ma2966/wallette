package com.arshapshap.core_network.di

import com.arshapshap.common.data.TokenManager
import com.arshapshap.core_network.data.managers.TokenManagerImpl
import dagger.Binds
import dagger.Module

@Module
interface NetworkBindsModule {

    @Binds
    fun bindTokenManager(tokenManagerImpl: TokenManagerImpl): TokenManager
}