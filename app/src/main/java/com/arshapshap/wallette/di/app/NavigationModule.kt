package com.arshapshap.wallette.di.app

import com.arshapshap.common.di.scopes.ApplicationScope
import com.arshapshap.feature_auth.presentation.screen.AuthorizationRouter
import com.arshapshap.feature_settings.presentation.SettingsRouter
import com.arshapshap.feature_statistics_impl.presentation.StatisticsRouter
import com.arshapshap.wallette.navigation.Navigator
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    @ApplicationScope
    @Provides
    fun provideNavigator(): Navigator = Navigator()

    @ApplicationScope
    @Provides
    fun provideStatisticsRouter(navigator: Navigator): StatisticsRouter = navigator

    @ApplicationScope
    @Provides
    fun provideAuthorizationRouter(navigator: Navigator): AuthorizationRouter = navigator

    @ApplicationScope
    @Provides
    fun provideSettingsRouter(navigator: Navigator): SettingsRouter = navigator
}