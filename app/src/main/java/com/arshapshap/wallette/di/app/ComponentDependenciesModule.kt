package com.arshapshap.wallette.di.app

import com.arshapshap.common.di.ComponentDependencies
import com.arshapshap.common.di.ComponentDependenciesKey
import com.arshapshap.wallette.di.main.MainDependencies
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ComponentDependenciesModule {

    @Binds
    @IntoMap
    @ComponentDependenciesKey(MainDependencies::class)
    fun provideMainDependencies(component: AppComponent): ComponentDependencies
}