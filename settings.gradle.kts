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
include(":core-designsystem")
include(":core-ui")
include(":feature-search")
include(":feature-repo")
include(":core-common")
include(":data-network")
include(":data-model")
include(":data-database")
include(":data")
