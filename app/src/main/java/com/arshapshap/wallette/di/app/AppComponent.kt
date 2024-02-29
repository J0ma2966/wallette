package com.arshapshap.wallette.di.app

import com.arshapshap.wallette.core.common.di.CommonApi
import com.arshapshap.wallette.core.common.di.scopes.ApplicationScope
import com.arshapshap.wallette.App
import com.arshapshap.wallette.di.main.MainDependencies
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        AppModule::class,
        NavigationModule::class,
        FeaturesModule::class,
        FeatureManagerModule::class,
        ComponentDependenciesModule::class
    ])
interface AppComponent : MainDependencies, CommonApi {

    companion object {

        fun init(application: App): AppComponent {
            return DaggerAppComponent
                .builder()
                .application(application)
                .build()
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}