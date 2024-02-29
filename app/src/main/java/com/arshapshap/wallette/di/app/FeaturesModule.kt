package com.arshapshap.wallette.di.app

import com.arshapshap.wallette.core.common.di.FeatureApiHolder
import com.arshapshap.wallette.core.common.di.FeatureContainer
import com.arshapshap.wallette.core.common.di.scopes.ApplicationScope
import com.arshapshap.wallette.core.db.di.DbApi
import com.arshapshap.wallette.core.db.di.DbHolder
import com.arshapshap.wallette.core.network.di.NetworkApi
import com.arshapshap.wallette.core.network.di.NetworkHolder
import com.arshapshap.wallette.feature.auth.di.AuthorizationFeatureApi
import com.arshapshap.wallette.feature.auth.di.AuthorizationFeatureHolder
import com.arshapshap.wallette.feature.statistics.di.StatisticsFeatureApi
import com.arshapshap.wallette.feature.statistics.di.StatisticsFeatureHolder
import com.arshapshap.wallette.App
import com.arshapshap.wallette.core.data.di.DataApi
import com.arshapshap.wallette.core.data.di.DataHolder
import com.arshapshap.wallette.feature.settings.di.SettingsFeatureApi
import com.arshapshap.wallette.feature.settings.di.SettingsFeatureHolder
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface FeaturesModule {

    @ApplicationScope
    @Binds
    fun provideFeatureContainer(application: App): FeatureContainer

    @ApplicationScope
    @Binds
    @ClassKey(StatisticsFeatureApi::class)
    @IntoMap
    fun provideStatisticsFeatureHolder(statisticsFeatureHolder: StatisticsFeatureHolder): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(AuthorizationFeatureApi::class)
    @IntoMap
    fun provideAuthorizationFeatureHolder(authorizationFeatureHolder: AuthorizationFeatureHolder): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(SettingsFeatureApi::class)
    @IntoMap
    fun provideSettingsFeatureHolder(settingsFeatureHolder: SettingsFeatureHolder): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(DbApi::class)
    @IntoMap
    fun provideDbFeature(dbHolder: DbHolder): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(DataApi::class)
    @IntoMap
    fun provideDataFeature(dataHolder: DataHolder): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(NetworkApi::class)
    @IntoMap
    fun provideNetworkFeature(networkHolder: NetworkHolder): FeatureApiHolder
}