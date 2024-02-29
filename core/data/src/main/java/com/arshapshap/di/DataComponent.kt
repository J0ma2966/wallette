package com.arshapshap.di

import com.arshapshap.common.di.CommonApi
import com.arshapshap.common.di.scopes.ApplicationScope
import com.arshapshap.core_db.di.DbApi
import com.arshapshap.core_network.di.NetworkApi
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