package com.arshapshap.wallette.core.common.di

interface FeatureContainer {

    fun <T> getFeature(key: Class<*>): T

    fun releaseFeature(key: Class<*>)

    fun commonApi(): com.arshapshap.wallette.core.common.di.CommonApi
}