package com.arshapshap.wallette.core.network.di

import android.content.Context
import android.content.SharedPreferences

interface NetworkDependencies {

    fun context(): Context

    fun sharedPreferences(): SharedPreferences
}