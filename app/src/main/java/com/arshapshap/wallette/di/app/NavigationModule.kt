package com.arshapshap.wallette.di.app

import com.arshapshap.wallette.core.common.di.scopes.ApplicationScope
import com.arshapshap.wallette.feature.auth.presentation.screen.AuthorizationRouter
import com.arshapshap.wallette.feature.settings.presentation.SettingsRouter
import com.arshapshap.wallette.feature.statistics.presentation.StatisticsRouter
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