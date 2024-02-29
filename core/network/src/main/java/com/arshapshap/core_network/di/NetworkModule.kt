package com.arshapshap.core_network.di

import com.arshapshap.common.di.scopes.ApplicationScope
import com.arshapshap.core_network.BuildConfig
import com.arshapshap.core_network.data.TokenInterceptor
import com.arshapshap.core_network.data.services.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideAccountsApiService(retrofit: Retrofit): AccountsApiService {
        return retrofit.create(
            AccountsApiService::class.java
        )
    }

    @Provides
    fun provideAuthorizationApiService(retrofit: Retrofit): AuthorizationApiService {
        return retrofit.create(
            AuthorizationApiService::class.java
        )
    }

    @Provides
    fun provideCategoriesApiService(retrofit: Retrofit): CategoriesApiService {
        return retrofit.create(
            CategoriesApiService::class.java
        )
    }

    @Provides
    fun provideTagsApiService(retrofit: Retrofit): TagsApiService {
        return retrofit.create(
            TagsApiService::class.java
        )
    }

    @Provides
    fun provideTransactionsApiService(retrofit: Retrofit): TransactionsApiService {
        return retrofit.create(
            TransactionsApiService::class.java
        )
    }

    @ApplicationScope
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.WALLETTE_BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun provideOkHttpClient(interceptor: TokenInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}