package com.arshapshap.wallette.core.data.di

import com.arshapshap.wallette.core.common.data.SettingsManager
import com.arshapshap.wallette.core.common.domain.repositories.*

interface DataApi {

    fun provideAccountsRepository(): AccountRepository

    fun provideAuthorizationRepository(): AuthorizationRepository

    fun provideCategoriesRepository(): CategoryRepository

    fun provideTagsRepository(): TagRepository

    fun provideTransactionsRepository(): TransactionRepository

    fun provideSettingsManager(): SettingsManager
}