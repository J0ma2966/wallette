pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
rootProject.name = "Wallette"
include(":app")
include(":feature:statistics")
include(":feature:auth")
include(":feature:settings")
include(":core:common")
include(":core:db")
include(":core:data")
include(":core:network")
