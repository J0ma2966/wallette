package com.arshapshap.di

import com.arshapshap.common.data.SettingsManager
import com.arshapshap.common.domain.repositories.*
import com.arshapshap.data.managers.SettingsManagerImpl
import com.arshapshap.data.managers.interfaces.*
import com.arshapshap.data.repositories.*
import com.arshapshap.data.repositories.remote.AccountRemoteRepositoryImpl
import com.arshapshap.data.repositories.remote.CategoryRemoteRepositoryImpl
import com.arshapshap.data.repositories.remote.TagRemoteRepositoryImpl
import com.arshapshap.data.repositories.remote.TransactionRemoteRepositoryImpl
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