package com.arshapshap.wallette.feature.statistics.di

import com.arshapshap.wallette.core.common.data.SettingsManager
import com.arshapshap.wallette.core.common.domain.repositories.AccountRepository
import com.arshapshap.wallette.core.common.domain.repositories.CategoryRepository
import com.arshapshap.wallette.core.common.domain.repositories.TagRepository
import com.arshapshap.wallette.core.common.domain.repositories.TransactionRepository

interface StatisticsDependencies {

    fun accountRepository(): AccountRepository

    fun categoryRepository(): CategoryRepository

    fun tagRepository(): TagRepository

    fun transactionRepository(): TransactionRepository

    fun settingsManager(): SettingsManager
}