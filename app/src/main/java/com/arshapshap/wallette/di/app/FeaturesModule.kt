package com.arshapshap.wallette.di.app

import com.arshapshap.common.di.FeatureApiHolder
import com.arshapshap.common.di.FeatureContainer
import com.arshapshap.common.di.scopes.ApplicationScope
import com.arshapshap.core_db.di.DbApi
import com.arshapshap.core_db.di.DbHolder
import com.arshapshap.core_network.di.NetworkApi
import com.arshapshap.core_network.di.NetworkHolder
import com.arshapshap.di.DataApi
import com.arshapshap.di.DataHolder
import com.arshapshap.feature_auth.di.AuthorizationFeatureApi
import com.arshapshap.feature_settings.di.SettingsFeatureApi
import com.arshapshap.feature_settings.di.SettingsFeatureHolder
import com.arshapshap.feature_auth.di.AuthorizationFeatureHolder
import com.arshapshap.feature_statistics_impl.di.StatisticsFeatureApi
import com.arshapshap.feature_statistics_impl.di.StatisticsFeatureHolder
import com.arshapshap.wallette.App
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