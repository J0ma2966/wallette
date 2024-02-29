package com.arshapshap.wallette.core.data.di

import android.content.Context
import android.content.SharedPreferences
import com.arshapshap.wallette.core.common.data.TokenManager
import com.arshapshap.wallette.core.db.AppDatabase
import com.arshapshap.wallette.core.network.data.services.AccountsApiService
import com.arshapshap.wallette.core.network.data.services.AuthorizationApiService
import com.arshapshap.wallette.core.network.data.services.CategoriesApiService
import com.arshapshap.wallette.core.network.data.services.TagsApiService
import com.arshapshap.wallette.core.network.data.services.TransactionsApiService

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