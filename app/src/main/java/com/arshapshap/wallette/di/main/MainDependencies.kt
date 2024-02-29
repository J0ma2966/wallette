package com.arshapshap.wallette.di.main

import com.arshapshap.wallette.core.common.di.ComponentDependencies
import com.arshapshap.wallette.navigation.Navigator

interface MainDependencies : com.arshapshap.wallette.core.common.di.ComponentDependencies {

    fun navigator(): Navigator
}