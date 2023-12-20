# GalleryApp

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.20-blue.svg)](https://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/gradle-8.0-green.svg)](https://gradle.org/)
[![Android Studio](https://img.shields.io/badge/Android%20Studio-2022.3.1%20%28Giraff%29-green)](https://developer.android.com/studio)
[![minSdkVersion](https://img.shields.io/badge/minSdkVersion-24-red)](https://developer.android.com/distribute/best-practices/develop/target-sdk)
[![targetSdkVersion](https://img.shields.io/badge/targetSdkVersion-34-orange)](https://developer.android.com/distribute/best-practices/develop/target-sdk)
<br/>

![graphic image](https://github.com/easyhooon/GalleryApp/assets/51016231/9103f6c5-643f-4b42-a0b1-8bf4121fd63a)

|Light Mode 1|Light Mode 2|
|:-----:|:-----:|
|<img width="240" src="https://github.com/easyhooon/GalleryApp/assets/51016231/736f9135-9705-488c-a747-cee78ab86f82">|<img width="240" src="https://github.com/easyhooon/GalleryApp/assets/51016231/4bf1d78c-4b81-42c6-a460-bccbf7eb1a9a">|

|Dark Mode 1|Dark Mode 2|
|:-----:|:-----:|
|<img width="240" src="https://github.com/easyhooon/GalleryApp/assets/51016231/42960488-9958-49ad-b21c-2a403a9c27c7">|<img width="240" src="https://github.com/easyhooon/GalleryApp/assets/51016231/9f07c603-42e6-42a0-92d1-b9ca822f3830">|

## Features

|피드 화면|Author로 사진 검색 기능|
|:-----:|:-----:|
|<img width="240" src="https://github.com/easyhooon/GalleryApp/assets/51016231/3bfa25e9-602f-4f3f-9136-c189e91f2b07">|<img width="240" src="https://github.com/easyhooon/GalleryApp/assets/51016231/0bddf21e-a690-471d-b7cb-c0f74bf2bdb9">|

|상세 화면|Zoom 기능|사진 저장 기능|
|:-----:|:-----:|:-----:|
|<img width="240" src="https://github.com/easyhooon/GalleryApp/assets/51016231/a54d3fa6-c19e-4bd5-ab6c-004e840cf80e">|<img width="240" src="https://github.com/easyhooon/GalleryApp/assets/51016231/1614d722-616d-46a6-82d8-346c7ebb1d8f">|<img width="240" src="https://github.com/easyhooon/GalleryApp/assets/51016231/70680d99-acda-442a-85df-ea65aa7708da">

## Development

### Required

- IDE : Android Studio Giraffe
- JDK : Java 17을 실행할 수 있는 JDK
- Kotlin Language : 1.9.20

### Language

- Kotlin

### Libraries

- AndroidX
  - Activity Compose
  - Core
  - Lifecycle & ViewModel Compose
  - Navigation
  - Paging3
  - StartUp
  - Splash

- Kotlin Libraries (Coroutine, Serialization, Immutable)
- Compose
  - Material3
  - Navigation

- Dagger Hilt
- Retrofit
- Timber
- Coil
- [Zoomable](https://github.com/usuiat/Zoomable)
- [LazyColumnScrollbar](https://github.com/nanihadesuka/LazyColumnScrollbar)

#### Test & Code analysis

- Ktlint
- Detekt

#### Gradle Dependency

- Gradle Version Catalog

## Architecture
Based on [Google App Architecture](https://developer.android.com/topic/architecture) similar to Clean Architecture

<img width="760" alt="image" src="https://github.com/easyhooon/watcha-assignment/assets/51016231/2837a3b6-32f8-46aa-a102-3fb5b3e3826a">

<img width="760" alt="image" src="https://github.com/easyhooon/watcha-assignment/assets/51016231/b29020a1-69aa-482b-8af4-ddb27122a440">

## Package Structure
```
├── app
│   ├── initializer
│   └── Application
├── build-logic
├── buildSrc
├── data
│   ├── datasource
│   ├── di
│   ├── mapper
│   ├── model
│   ├── paging
│   ├── repository
│   ├── service
│   └── util
├── domain
│   ├── entity
│   ├── repository
│   └── usecase
├── presentation
│   ├── mapper
│   ├── model
│   ├── ui
│   └── util
├── gradle
│   └── libs.versions.toml
└── report
    ├── compose-metrics
    └── compose-reports
```

# ToDo
- [ ] LazyColumnScrollbar Custom (갤럭시 핸드폰의 갤러리의 스크롤바 처럼 위아래 화살표 구현)
- [ ] BuildType, Flavor 적용 
