
# MovLan 🎦 

MovLan is a sample project movie listing Android app, which uses the [TheMovieDb](https://www.themoviedb.org/) API.

## Android development

MovLan is an sample project which attempts to use the latest libraries and tools. As a summary:

 * Whole code base is written in [Kotlin](https://kotlinlang.org/)
 * Uses Coroutines 
 * Uses the [Architecture Components](https://developer.android.com/topic/libraries/architecture/):
    * Room database 
    * LiveData and Lifecycle-components
    * Paging
* Uses [dagger-android](https://google.github.io/dagger/android.html) for dependency injection

## Architecture
The app uses MVVM architecture which fits more with the latest libraries used (ViewModel and Room)

### Code style
Uses the default Kotlin code style built-in AndroidStudio

### API keys
At the moment the MovieDB API key is left in the project to make it easier to build, as future improvment it will be moved to the CI instance and readin from the gradle properties.

## License

```
                    GNU GENERAL PUBLIC LICENSE
                       Version 3, 29 June 2007

 Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
 Everyone is permitted to copy and distribute verbatim copies
 of this license document, but changing it is not allowed.

```
