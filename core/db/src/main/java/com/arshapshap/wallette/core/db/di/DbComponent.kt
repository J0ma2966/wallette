package com.arshapshap.wallette.core.db.di

import com.arshapshap.wallette.core.common.di.scopes.ApplicationScope
import dagger.Component

@Component(
    modules = [
        DbModule::class
    ],
    dependencies = [
        DbDependencies::class
    ]
)
@ApplicationScope
abstract class DbComponent : DbApi {

    @Component(
        dependencies = [
            com.arshapshap.wallette.core.common.di.CommonApi::class
        ]
    )
    interface DbDependenciesComponent : DbDependencies
}