package com.arshapshap.wallette.core.data.di

import com.arshapshap.wallette.core.common.data.SettingsManager
import com.arshapshap.wallette.core.common.domain.repositories.*
import com.arshapshap.wallette.core.data.managers.SettingsManagerImpl
import com.arshapshap.wallette.core.data.managers.interfaces.*
import com.arshapshap.wallette.core.data.repositories.*
import com.arshapshap.wallette.core.data.repositories.remote.AccountRemoteRepositoryImpl
import com.arshapshap.wallette.core.data.repositories.remote.CategoryRemoteRepositoryImpl
import com.arshapshap.wallette.core.data.repositories.remote.TagRemoteRepositoryImpl
import com.arshapshap.wallette.core.data.repositories.remote.TransactionRemoteRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface DataBindsModule {

    @Binds
    fun bindSettingsManager(settingsManagerImpl: SettingsManagerImpl): SettingsManager

    @Binds
    fun bindAccountsRepository(repositoryImpl: AccountRepositoryImpl): AccountRepository

    @Binds
    fun bindAuthorizationRepository(repositoryImpl: AuthorizationRepositoryImpl): AuthorizationRepository

    @Binds
    fun bindCategoriesRepository(repositoryImpl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    fun bindTagsRepository(repositoryImpl: TagRepositoryImpl): TagRepository

    @Binds
    fun bindTransactionsRepository(repository: TransactionRepositoryImpl): TransactionRepository

    @Binds
    fun bindAccountsRemoteRepository(repositoryImpl: AccountRemoteRepositoryImpl): AccountRemoteRepository

    @Binds
    fun bindCategoriesRemoteRepository(repositoryImpl: CategoryRemoteRepositoryImpl): CategoryRemoteRepository

    @Binds
    fun bindTagsRemoteRepository(repositoryImpl: TagRemoteRepositoryImpl): TagRemoteRepository

    @Binds
    fun bindTransactionsRemoteRepository(repository: TransactionRemoteRepositoryImpl): TransactionRemoteRepository
}