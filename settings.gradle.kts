pluginManagement {
    plugins {
        id("org.jetbrains.kotlin.android") version "1.9.24" apply false
        // Add the dependency for the Google services Gradle plugin
        id("com.google.gms.google-services") version "4.4.4" apply false
    }
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}


rootProject.name = "Guess_what_ai"
include(":app")
 