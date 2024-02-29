package com.arshapshap.wallette.di.main

import com.arshapshap.common.di.ComponentDependencies
import com.arshapshap.wallette.navigation.Navigator

interface MainDependencies : ComponentDependencies {

    fun navigator(): Navigator
}