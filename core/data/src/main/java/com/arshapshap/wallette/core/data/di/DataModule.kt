package com.arshapshap.wallette.core.data.di

import com.arshapshap.wallette.core.common.di.scopes.ApplicationScope
import com.arshapshap.wallette.core.db.AppDatabase
import com.arshapshap.wallette.core.db.dao.AccountDao
import com.arshapshap.wallette.core.db.dao.CategoryDao
import com.arshapshap.wallette.core.db.dao.SyncRequestDao
import com.arshapshap.wallette.core.db.dao.TagDao
import com.arshapshap.wallette.core.db.dao.TransactionDao
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @ApplicationScope
    fun provideGson(): Gson
        = Gson()

    @Provides
    @ApplicationScope
    fun provideAccountDao(appDatabase: AppDatabase): AccountDao
        = appDatabase.accountDao()

    @Provides
    @ApplicationScope
    fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao
            = appDatabase.categoryDao()

    @Provides
    @ApplicationScope
    fun provideTagDao(appDatabase: AppDatabase): TagDao
            = appDatabase.tagDao()

    @Provides
    @ApplicationScope
    fun provideTransactionDao(appDatabase: AppDatabase): TransactionDao
            = appDatabase.transactionDao()

    @Provides
    @ApplicationScope
    fun provideSyncRequestDao(appDatabase: AppDatabase): SyncRequestDao
            = appDatabase.syncRequestsDao()
}