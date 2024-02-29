package com.arshapshap.wallette.core.data.di

import com.arshapshap.wallette.core.common.di.CommonApi
import com.arshapshap.wallette.core.common.di.scopes.ApplicationScope
import com.arshapshap.wallette.core.db.di.DbApi
import com.arshapshap.wallette.core.network.di.NetworkApi
import dagger.Component

@Component(
    modules = [
        DataBindsModule::class,
        DataModule::class
    ],
    dependencies = [
        DataDependencies::class
    ]
)
@ApplicationScope
abstract class DataComponent : DataApi {

    @Component.Builder
    interface Builder {

        fun withDependencies(deps: DataDependencies): Builder

        fun build(): DataComponent
    }

    @Component(
        dependencies = [
            CommonApi::class,
            DbApi::class,
            NetworkApi::class
        ]
    )
    interface DataDependenciesComponent : DataDependencies
}