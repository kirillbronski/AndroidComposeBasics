pluginManagement {
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

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "AndroidComposeBasics"
include(":app")
include(":l14-lazycolumn")
include(":l15-lazycolumn")
include(":l16-lazycolumn")
include(":l17-compositionlocal")
include(":l18-compositionlocal")
include(":l19-constraintlayout")
include(":l20-material3")
include(":l21-sideeffects")
include(":l22-sideeffects")
include(":l24-scaffold")
include(":l25-topappbar")
include(":l26-navigationbar")
include(":l27-backhandler")
include(":l28-dropdownmenu")
