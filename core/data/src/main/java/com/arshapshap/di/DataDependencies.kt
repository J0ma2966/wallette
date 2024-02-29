package com.arshapshap.di

import android.content.Context
import android.content.SharedPreferences
import com.arshapshap.common.data.TokenManager
import com.arshapshap.core_db.AppDatabase
import com.arshapshap.core_network.data.services.*

interface DataDependencies {

    fun context(): Context

    fun sharedPreferences(): SharedPreferences

    fun appDatabase(): AppDatabase

    fun accountsApiService(): AccountsApiService

    fun authorizationApiService(): AuthorizationApiService

    fun categoriesApiService(): CategoriesApiService

    fun tagsApiService(): TagsApiService

    fun transactionsApiService(): TransactionsApiService

    fun tokenManager(): TokenManager
}