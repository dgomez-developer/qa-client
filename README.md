# qa-client

Android Client for que QA Server.

In this example app you will find different inplementations depending on the branch:

* **develop**: Implementation using LiveData + ViewModels + Executors + Room
* **feature/example-with-no-architecture-components**: Implementation using traditional Java Classes.
* **example-with-coroutines**: Based on **develop** branch replacing Executors by coroutines.
* **example-with-flow**: Based on **example-with-coroutines** branch replacing LiveDatas with Flows.

## References

* [Fernando Cejas - Architecting Reloaded](https://fernandocejas.com/2018/05/07/architecting-android-reloaded/)
* [Jose Alcérreca - LiveData beyond the ViewModel - Reactive patterns using Transformations and MediatorLiveData](https://medium.com/androiddevelopers/livedata-beyond-the-viewmodel-reactive-patterns-using-transformations-and-mediatorlivedata-fda520ba00b7)
* [Chibatching - Kotlin Coroutines Flow + LiveData + Data Binding](https://medium.com/@chibatching/kotlin-coroutines-flow-livedata-data-binding-b3436c4ca818)

## License

    Copyright 2018 Debora Gomez

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.