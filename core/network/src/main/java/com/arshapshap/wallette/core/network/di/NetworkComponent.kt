package com.arshapshap.wallette.core.network.di

import com.arshapshap.wallette.core.common.di.CommonApi
import com.arshapshap.wallette.core.common.di.scopes.ApplicationScope
import dagger.Component

@Component(
    dependencies = [
        NetworkDependencies::class
    ],
    modules = [
        NetworkModule::class,
        NetworkBindsModule::class
    ]
)
@ApplicationScope
abstract class NetworkComponent : NetworkApi {

    @Component(
        dependencies = [
            com.arshapshap.wallette.core.common.di.CommonApi::class
        ]
    )
    interface NetworkDependenciesComponent : NetworkDependencies
}