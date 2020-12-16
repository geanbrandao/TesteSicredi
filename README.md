# Teste sicredi

- [x] Add Navigation componet
- [x] Add Splashscreen
- [x] Add Motion Layout in splashscreen
- [x] Add retrofit (min sdk 21) - library
- [x] Create GET using Retrofit an API
- [x] Create POST using Retrofit an API
- [x] Add RXjava to observe the changes - library
- [x] Add viewModel lifecycle - library
- [x] Add dark/light theme (maybe prefs screen) 
- [x] Choose de colors to compoese the theme
- [x] Add Events screnn to list the events
- [x] Include navigation action to go to details
- [x] Cache the result using retrofit and time
- [x] Add SwipeRefresh in the recycler
- [x] Create details event screen
- [x] Configure args insede navigation graph
- [x] Dipose de disposable on onStop
- [x] Make sure the navigation componet handle the recreate event on activity on lower SDK
- [x] I have to upgrade the min SDK to 21, than i can use better tools
- [x] Create button to share event
- [ ] Testes?

# Framework
* [Retrofit](https://square.github.io/retrofit/)
  * This is used to manage the api request

* [RxJava](http://reactivex.io/documentation)
  * This is used to avoid blocking the UI. That way, the API result can be obtained when are finished, and then the UI can be filled

* [Koin](https://insert-koin.io/)
  * This is used to manager and inject the dependencys on the code. For example, this prevents multiple instances of the same reference
  * This is used with Android Components

* [Timber](https://timber.github.io/docs/)
  * This is used to log and debug the app, and prevent any log appear in realese mode

* [Android Components](https://developer.android.com/topic/libraries/architecture)
  * Import part of the app, grant a more clean code using MVVM structure
  * Naviagation Components is a great, because it hadle with when app is recreate without losing the current screen fragment. Beyond the safe args
  
* [Glide](https://github.com/bumptech/glide)
  * Used to fill imageView, download and cache url image more eficient

# Explanation
- To choose de coler i use [color tool](https://material.io/resources/color/) from material design