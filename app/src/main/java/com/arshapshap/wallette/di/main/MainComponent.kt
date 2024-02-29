package com.arshapshap.wallette.di.main

import androidx.appcompat.app.AppCompatActivity
import com.arshapshap.wallette.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        MainDependencies::class
    ]
)
interface MainComponent {
    companion object {

        fun init(activity: AppCompatActivity, deps: MainDependencies): MainComponent {
            return DaggerMainComponent.factory().create(activity, deps)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance activity: AppCompatActivity,
            deps: MainDependencies
        ): MainComponent
    }

    fun inject(mainActivity: MainActivity)
}