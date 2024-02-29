package com.arshapshap.wallette.di.app

import android.content.Context
import android.content.SharedPreferences
import com.arshapshap.common.di.scopes.ApplicationScope
import com.arshapshap.wallette.App
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @ApplicationScope
    @Provides
    fun provideContext(app: App): Context {
        return app
    }

    @ApplicationScope
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences
            = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
}