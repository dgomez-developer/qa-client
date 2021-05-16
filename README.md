# qa-client

Android Client for que QA Server.

In this example app you will find different inplementations depending on the branch:

* **develop**: Implementation using LiveData + ViewModels + Executors + Room
* **feature/example-with-no-architecture-components**: Implementation using traditional Java Classes.
* **example-with-coroutines**: Based on **develop** branch replacing Executors by coroutines.
* **example-with-flow**: Based on **example-with-coroutines** branch replacing LiveDatas with Flows.
* **example-with-navigation-components**: Based on **example-with-flow** branch using navigation components to connect 2 fragments
* **example-with-tabs**: Based on **example-with-navigation-components** branch using navigation components in a tab bar Instagram/Youtube style.

## How to launch a DL via console in tab bar example

```aidl
adb shell am start -W -a android.intent.action.VIEW -d https://www.dgomez.developer.com/text/contact com.dgomez.developer.architecture.components.qa_client
```

## GraphQL support

To download your own GraphQL schema, apollo library offer a gradle task:

```
./gradlew downloadApolloSchema --endpoint="http://localhost:5000/graphql" --schema="app/src/main/graphql/com/dgomez/developer/architecture/components/qa_client/schema.json"

```

Generating models automatically by using Apollo gradle task:

```
 ./gradlew generateApolloSources
```

## References

* [Fernando Cejas - Architecting Reloaded](https://fernandocejas.com/2018/05/07/architecting-android-reloaded/)
* [Jose Alcérreca - LiveData beyond the ViewModel - Reactive patterns using Transformations and MediatorLiveData](https://medium.com/androiddevelopers/livedata-beyond-the-viewmodel-reactive-patterns-using-transformations-and-mediatorlivedata-fda520ba00b7)
* [Chibatching - Kotlin Coroutines Flow + LiveData + Data Binding](https://medium.com/@chibatching/kotlin-coroutines-flow-livedata-data-binding-b3436c4ca818)
* [Ebi Igweze - Instagram style navigation using Navigation Component](https://android.jlelse.eu/instagram-style-navigation-using-navigation-component-854037cf1389)

## License

    Copyright 2019 Debora Gomez

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.