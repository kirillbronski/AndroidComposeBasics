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
include(":l29-simplenavigation")
include(":l33-navigationstack")
include(":l33-navigationstack:navigation")
include(":l34-navigationscreens:navigation")
include(":l35-navigationscreensstate:navigation")
include(":l36-navigation-environment:navigation")
include(":l37-navigation-args:navigation")
include(":l38-nav-communication:navigation")
include(":l39-nav-multiple-stacks:navigation")
include(":l40-nav-deep-links:navigation")
include(":l41-nav-view-models:navigation")
include(":l42-nav-view-models-hilt:navigation")
include(":l43-nav-component")
include(":l44-nav-component-hilt")
include(":l45-nav-component-hiltViewModel")
include(":l46-nav-component-environment")
include(":l46_1-nav-component-environment")
include(":l47-nav-component-args")
include(":l48-nav-component-refactoring")
include(":l49-nav-component-refactoring2")
include(":l50-nav-component-multistack")
include(":l51-nav-component-deep-links")
include(":l52-errors")
include(":l53-event-and-state")
include(":l54-dialogs")
include(":l55-animations")
include(":l54-dialogs")
include(":l56-animation-scrossfade-animatedcontent")
