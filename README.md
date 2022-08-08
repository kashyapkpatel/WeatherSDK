# Weather SDK and App
## _This App is built to Accomplish Following Requirements._

## Screen 1

- There are two segments, 1) Map 2) Location List
- Just tap on the any valid location on the map and there you go.
- Once location is added to the DB, just tap it to see more weather info.

## Screen 2

- Using Restful Web APIs, it loads weather info as well as forecast info.

## Screen 3

- Tap info button on the Home screen to find some technical details on what different component is used in this app.

> Note: As the API key used for GOOGLE_API_KEY is secret and confidential, You need to update [secure.properties] with contents as below for this project to run successfully.

  ```sh
GOOGLE_API_KEY = <YourApiKey>
```

## Run Unit Tests

To run unit test cases, use following command.

```sh
gradlew test
```