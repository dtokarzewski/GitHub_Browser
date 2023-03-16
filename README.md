GitHub Browser
===========================================================

This project is sample offline-first, redux-style Android app. It's purpose is to be a
playground for testing various solutions.

Introduction
-------------

### Functionality
The app is very basic GitHub browser. It allows you to search for GitHub repos and see it's details
and commits list in details screen.

### Building
No special operations needed - just run project in Android Studio.

### Testing
Right now project has couple of basic instrumentation tests verifying app navigation with Jetpack Compose
and UseCase unit tests for domain layer verification.

TODO: Add abstraction layer in UseCases for ViewModels isolation and write ViewModel tests

### Tech stack
Project follows Google's architecture recommendations and uses Architecture Components. Here are main
components/libraries used:

* Jetpack (with Compose)
* Material Design 3
* Kotlin Coroutines with FLow
* Room
* Hilt
* Retrofit
* OkHttp

### Architecture
Project have multi-module architecture. It's an overkill to make such complex architecture for such
a small project, but it's goal was to make it as if it was big commercial project.

It consists of following layers:

#### Core
All `core-` modules contains classes used across the app.

#### Data layer
Offline first source of data in the app - all data fetched from network is saved in database. UI and
domain layers get data from database - never from network directly.

* `data-network` module fetches data from GitHub sever
* `data-database` module stores data in Room database
* `data` module orchestrates fetching, storing and mapping data from previous two
* `data-model` module stores data models used by business and ui layers. For proper layers separation 
`data-network` and `data-database` modules have their own equivalent of those models.

#### Domain layer
Place for business logic represented by UseCases.

#### UI layer
* UI elements build using `Jetpack Compose`
* `ViewModel`s
* Stored in `feature-xxx` modules

### TODO
* Add abstraction layer in UseCases for ViewModel isolation
* Write ViewModel's tests
* Write Repositories tests
* Configure CI
* Add some styling