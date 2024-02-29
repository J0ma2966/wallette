package com.arshapshap.wallette.core.network.di

import com.arshapshap.wallette.core.common.data.TokenManager
import com.arshapshap.wallette.core.network.data.services.*

interface NetworkApi {

    fun accountsApiService(): AccountsApiService

    fun authorizationApiService(): AuthorizationApiService

    fun categoriesApiService(): CategoriesApiService

    fun tagsApiService(): TagsApiService

    fun transactionsApiService(): TransactionsApiService

    fun tokenManager(): TokenManager
}