# WatchGhibli

[![Built on top of](https://img.shields.io/badge/from-kotlin--android--template-brightgreen?logo=android)](https://github.com/cortinico/kotlin-android-template?ref=androidrepo.com) ![Pre Merge Checks](https://github.com/victormordur/WatchGhibli/workflows/Pre%20Merge%20Checks/badge.svg)  ![License](https://img.shields.io/github/license/victormordur/WatchGhibli.svg)  ![Language](https://img.shields.io/github/languages/top/victormordur/WatchGhibli?color=blue&logo=kotlin)

This is basically a personal showcase project consisting on an Android app that uses the Jetpack Compose framework to implement the UI layer.

The app is aimed to retrieve a list of Studio Gihbli movies from the cute [Studio Ghibli API](https://ghibliapi.herokuapp.com/), allowing users to add them to a persistent watch-list locally in the app. Additionally, the movies can also be marked as watched, thus being moved from the watch-list into one last section for already watched movies.

As you can see by the current status of the project, all this is still work in progress.


## Template vs Project-specific Features

The current project is built on top of [kotlin-android-template](https://github.com/cortinico/kotlin-android-template?ref=androidrepo.com), which according to its author _is focused on delivering a project with **static analysis** and **continuous integration** already in place_ and _lets you create an Android/Kotlin project and be up and running in a few seconds_. 

### Template Features :art:

Features inherited from the template:

- 100% Kotlin-only poject.
- Sample Espresso, Instrumentation & JUnit tests.
- 100% Gradle Kotlin DSL setup.
- CI Setup with GitHub Actions. -> checks the gradle wrapper and runs the `build`, `check` tasks
- Dependency versions managed via `buildSrc`.
- Kotlin Static Analysis via `ktlint` and `detekt`. -> I find it very useful when combined with the Android Studio detekt plugin, since it provides live feedback on lint checks as you code.  
- Issues Template -> bug report + feature request.
- Pull Request Template.

### Project-specific Features :framed_picture: 

What I added on top of the template:

- Dependency injection with `Koin`.
- SQLite DB with `SQLDelight` for schema management.
- Networking layer with `Ktor`.
- Data, domain and presentation layers. -> clean-architecture approach.
- Extensive use of Flow.
- Android Compose UI.
- High test coverage.
