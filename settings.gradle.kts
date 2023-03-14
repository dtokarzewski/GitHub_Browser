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
include(":core-common")
include(":core-ui")
include(":core-designsystem")
include(":data")
include(":data-network")
include(":data-model")
include(":data-database")
include(":domain")
include(":feature-search")
include(":feature-repo")
