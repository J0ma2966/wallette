package com.arshapshap.feature_statistics_impl.di

import com.arshapshap.common.domain.repositories.AccountRepository
import com.arshapshap.common.domain.repositories.CategoryRepository
import com.arshapshap.common.domain.repositories.TagRepository
import com.arshapshap.common.domain.repositories.TransactionRepository

interface StatisticsDependencies {

    fun accountRepository(): AccountRepository

    fun categoryRepository(): CategoryRepository

    fun tagRepository(): TagRepository

    fun transactionRepository(): TransactionRepository
}