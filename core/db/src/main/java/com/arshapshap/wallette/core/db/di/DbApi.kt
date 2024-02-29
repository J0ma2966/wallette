package com.arshapshap.wallette.core.db.di

import com.arshapshap.wallette.core.db.AppDatabase

interface DbApi {

    fun provideDatabase(): AppDatabase
}