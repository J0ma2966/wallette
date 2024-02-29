package com.arshapshap.di

import com.arshapshap.common.data.SettingsManager
import com.arshapshap.common.domain.repositories.*

interface DataApi {

    fun provideAccountsRepository(): AccountRepository

    fun provideAuthorizationRepository(): AuthorizationRepository

    fun provideCategoriesRepository(): CategoryRepository

    fun provideTagsRepository(): TagRepository

    fun provideTransactionsRepository(): TransactionRepository

    fun provideSettingsManager(): SettingsManager
}