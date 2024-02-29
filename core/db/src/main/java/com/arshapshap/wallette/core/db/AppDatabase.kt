package com.arshapshap.wallette.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arshapshap.wallette.core.db.models.SyncRequest
import com.arshapshap.wallette.core.db.models.TransactionTagCrossRef
import com.arshapshap.wallette.core.db.models.entities.AccountLocal
import com.arshapshap.wallette.core.db.models.entities.CategoryLocal
import com.arshapshap.wallette.core.db.models.entities.TagLocal
import com.arshapshap.wallette.core.db.models.entities.TransactionLocal
import com.arshapshap.wallette.core.db.dao.AccountDao
import com.arshapshap.wallette.core.db.dao.CategoryDao
import com.arshapshap.wallette.core.db.dao.SyncRequestDao
import com.arshapshap.wallette.core.db.dao.TagDao
import com.arshapshap.wallette.core.db.dao.TransactionDao

@Database(
    version = 1,
    entities = [
        AccountLocal::class,
        CategoryLocal::class,
        TagLocal::class,
        TransactionLocal::class,
        TransactionTagCrossRef::class,
        SyncRequest::class
    ])
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun get(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "app.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }

    abstract fun accountDao(): AccountDao

    abstract fun categoryDao(): CategoryDao

    abstract fun tagDao(): TagDao

    abstract fun transactionDao(): TransactionDao

    abstract fun syncRequestsDao(): SyncRequestDao
}