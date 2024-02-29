package com.arshapshap.core_db.di

import com.arshapshap.core_db.AppDatabase

interface DbApi {

    fun provideDatabase(): AppDatabase
}