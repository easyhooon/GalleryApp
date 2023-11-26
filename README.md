# daangn-assignment

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.20-blue.svg)](https://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/gradle-8.0-green.svg)](https://gradle.org/)
[![Android Studio](https://img.shields.io/badge/Android%20Studio-2022.3.1%20%28Giraff%29-green)](https://developer.android.com/studio)
[![minSdkVersion](https://img.shields.io/badge/minSdkVersion-24-red)](https://developer.android.com/distribute/best-practices/develop/target-sdk)
[![targetSdkVersion](https://img.shields.io/badge/targetSdkVersion-34-orange)](https://developer.android.com/distribute/best-practices/develop/target-sdk)
<br/>

GalleryApp

## Features

|피드 화면|author로 사진 검색 기능|
|:-----:|:-----:|

|상세 화면|zoom 기능|사진 저장 기능|
|:-----:|:-----:|:-----:|

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
│   └── ui
├── gradle
│   └── libs.versions.toml
└── report
    ├── compose-metrics
    └── compose-reports
```
