package com.arshapshap.core_network.di

import com.arshapshap.common.data.TokenManager
import com.arshapshap.core_network.data.services.*

interface NetworkApi {

    fun accountsApiService(): AccountsApiService

    fun authorizationApiService(): AuthorizationApiService

    fun categoriesApiService(): CategoriesApiService

    fun tagsApiService(): TagsApiService

    fun transactionsApiService(): TransactionsApiService

    fun tokenManager(): TokenManager
}