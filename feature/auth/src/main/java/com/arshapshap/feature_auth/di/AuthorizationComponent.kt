package com.arshapshap.feature_auth.di

import com.arshapshap.common.di.scopes.AuthorizationScope
import com.arshapshap.di.DataApi
import com.arshapshap.feature_auth.presentation.screen.AuthorizationRouter
import com.arshapshap.feature_auth.presentation.screen.login.LoginFragment
import com.arshapshap.feature_auth.presentation.screen.login.LoginViewModel
import com.arshapshap.feature_auth.presentation.screen.register.RegisterFragment
import com.arshapshap.feature_auth.presentation.screen.register.RegisterViewModel
import dagger.BindsInstance
import dagger.Component

@AuthorizationScope
@Component(
    dependencies = [
        AuthorizationDependencies::class
    ]
)
interface AuthorizationComponent : AuthorizationFeatureApi {

    @Component.Builder
    interface Builder {

        fun build(): AuthorizationComponent

        fun withDependencies(deps: AuthorizationDependencies): Builder

        @BindsInstance
        fun router(router: AuthorizationRouter): Builder
    }

    @Component(
        dependencies = [
            DataApi::class
        ]
    )
    interface AuthorizationDependenciesComponent : AuthorizationDependencies

    fun inject(loginFragment: LoginFragment)

    fun inject(registerFragment: RegisterFragment)

    fun loginViewModel(): LoginViewModel.Factory

    fun registerViewModel(): RegisterViewModel.Factory
}