pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "GitHub Browser"

include(":app")
include(":core:common")
include(":core:designsystem")
include(":core:test")
include(":core:ui")
include(":data")
include(":data:database")
include(":data:network")
include(":data:model")
include(":data:test")
include(":domain")
include(":feature:repo")
include(":feature:search")
