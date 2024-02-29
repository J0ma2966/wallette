package com.arshapshap.wallette.core.db.di

import android.content.Context
import com.arshapshap.wallette.core.common.di.scopes.ApplicationScope
import com.arshapshap.wallette.core.db.AppDatabase
import com.arshapshap.wallette.core.db.dao.AccountDao
import com.arshapshap.wallette.core.db.dao.CategoryDao
import com.arshapshap.wallette.core.db.dao.SyncRequestDao
import com.arshapshap.wallette.core.db.dao.TagDao
import com.arshapshap.wallette.core.db.dao.TransactionDao
import dagger.Module
import dagger.Provides

@Module
class DbModule {

    @Provides
    @ApplicationScope
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.get(context)
    }

    @Provides
    @ApplicationScope
    fun provideAccountDao(appDatabase: AppDatabase): AccountDao {
        return appDatabase.accountDao()
    }

    @Provides
    @ApplicationScope
    fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao {
        return appDatabase.categoryDao()
    }

    @Provides
    @ApplicationScope
    fun provideTagDao(appDatabase: AppDatabase): TagDao {
        return appDatabase.tagDao()
    }

    @Provides
    @ApplicationScope
    fun provideTransactionDao(appDatabase: AppDatabase): TransactionDao {
        return appDatabase.transactionDao()
    }

    @Provides
    @ApplicationScope
    fun provideSyncRequestDao(appDatabase: AppDatabase): SyncRequestDao {
        return appDatabase.syncRequestsDao()
    }
}