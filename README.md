# WatchGhibli

[![Built on top of](https://img.shields.io/badge/from-kotlin--android--template-brightgreen?logo=android)](https://github.com/cortinico/kotlin-android-template?ref=androidrepo.com) ![Pre Merge Checks](https://github.com/victormordur/WatchGhibli/workflows/Pre%20Merge%20Checks/badge.svg)  ![License](https://img.shields.io/github/license/victormordur/WatchGhibli.svg)  ![Language](https://img.shields.io/github/languages/top/victormordur/WatchGhibli?color=blue&logo=kotlin)

This is basically a personal showcase project consisting on an Android app that uses the Jetpack Compose framework to implement the UI layer.

The app is aimed to retrieve a list of Studio Gihbli movies from the cute [Studio Ghibli API](https://ghibliapi.herokuapp.com/), allowing users to add them to a persistent watch-list locally in the app. Additionally, the movies can also be marked as watched, thus being moved from the watch-list into one last section for already watched movies.

As you can see by the current status of the project, all this is still work in progress.


## Template Features 🎨

The current project is built on top of [kotlin-android-template](https://github.com/cortinico/kotlin-android-template?ref=androidrepo.com), which according to its author _is focused on delivering a project with **static analysis** and **continuous integration** already in place_ and _lets you create an Android/Kotlin project and be up and running in a few seconds_. I also borrowed the descritpion for some of the template-provided features in the following sections, as well as the list of features this project is re-using: 

- 100% Kotlin-only poject.
- Sample Espresso, Instrumentation & JUnit tests.
- 100% Gradle Kotlin DSL setup.
- CI Setup with GitHub Actions.
- Dependency versions managed via `buildSrc`.
- Kotlin Static Analysis via `ktlint` and `detekt`.
- Issues Template (bug report + feature request).
- Pull Request Template.

## Gradle Setup 🐘

The main reason to use a template was to re-use [**Gradle Kotlin DSL**](https://docs.gradle.org/current/userguide/kotlin_dsl.html) as well as the [Plugin DSL](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block) to setup the build.

Dependencies are centralized inside the [Dependencies.kt](buildSrc/src/main/java/Dependencies.kt) file in the `buildSrc` folder. This provides convenient auto-completion when writing your gradle files.

## Static Analysis 🔍

As the oiginal template, the project uses [**ktlint**](https://github.com/pinterest/ktlint) with the [ktlint-gradle](https://github.com/jlleitschuh/ktlint-gradle) plugin to format your code. To reformat all the source code as well as the buildscript you can run the `ktlintFormat` gradle task.

The same can be said for [**detekt**](https://github.com/detekt/detekt) to analyze the source code, with the configuration that is stored in the [detekt.yml](config/detekt/detekt.yml) file (the file has been generated with the `detektGenerateConfig` task). I find it very useful when combined with the Android Studio detekt plugin, since it provides live feedback on lint checks as you code.  

## CI ⚙️

This project CI is running on [**GitHub Actions**] although only two worflows from the oiginal template remain. Basically, the ones to ensure that the build does not break and tests are passing before merging a PR into the main branch:
- [Validate Gradle Wrapper](.github/workflows/gradle-wrapper-validation.yml) - Will check that the gradle wrapper has a valid checksum
- [Pre Merge Checks](.github/workflows/pre-merge.yaml) - Will run the `build`, `check` tasks.


## Publishing 🚀

Publishing support has been removed compared to the template, since it was aimed to publish libraries/artifacts and the current project just consist of an stand-alone app.

## Contributing 🤝

Feel free to open a issue or submit a pull request for any bugs/improvements.
