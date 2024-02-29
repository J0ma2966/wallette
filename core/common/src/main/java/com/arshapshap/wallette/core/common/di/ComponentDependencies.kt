package com.arshapshap.wallette.core.common.di

import android.app.Activity
import dagger.MapKey
import kotlin.reflect.KClass

interface ComponentDependencies

typealias ComponentDependenciesProvider = Map<Class<out com.arshapshap.wallette.core.common.di.ComponentDependencies>, @JvmSuppressWildcards com.arshapshap.wallette.core.common.di.ComponentDependencies>

interface HasComponentDependencies {
    val dependencies: com.arshapshap.wallette.core.common.di.ComponentDependenciesProvider
}

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ComponentDependenciesKey(val value: KClass<out com.arshapshap.wallette.core.common.di.ComponentDependencies>)

inline fun <reified T : com.arshapshap.wallette.core.common.di.ComponentDependencies> Activity.findComponentDependencies(): T {
    return findComponentDependenciesProvider()[T::class.java] as T
}

fun Activity.findComponentDependenciesProvider(): com.arshapshap.wallette.core.common.di.ComponentDependenciesProvider {
    val hasDaggerProviders = when (application) {
        is com.arshapshap.wallette.core.common.di.HasComponentDependencies -> application as com.arshapshap.wallette.core.common.di.HasComponentDependencies
        else -> throw IllegalStateException("Can not find suitable dagger provider for $this")
    }
    return hasDaggerProviders.dependencies
}