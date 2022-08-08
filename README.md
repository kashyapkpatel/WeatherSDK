# Weather SDK and App
## Prerequisite: 
Android Studio Arctic Fox or later is required. If you haven't already done so, [Download](https://developer.android.com/studio/index.html) and install it.

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

https://user-images.githubusercontent.com/11437005/183509037-656e9f51-44c0-428e-ab57-9cb9c467a78a.mp4

