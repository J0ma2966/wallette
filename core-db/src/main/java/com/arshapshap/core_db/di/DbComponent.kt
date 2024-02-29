package com.arshapshap.core_db.di

import com.arshapshap.common.di.CommonApi
import com.arshapshap.common.di.scopes.ApplicationScope
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
            CommonApi::class
        ]
    )
    interface DbDependenciesComponent : DbDependencies
}