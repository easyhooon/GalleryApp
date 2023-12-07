# daangn-assignment

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.20-blue.svg)](https://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/gradle-8.0-green.svg)](https://gradle.org/)
[![Android Studio](https://img.shields.io/badge/Android%20Studio-2022.3.1%20%28Giraff%29-green)](https://developer.android.com/studio)
[![minSdkVersion](https://img.shields.io/badge/minSdkVersion-24-red)](https://developer.android.com/distribute/best-practices/develop/target-sdk)
[![targetSdkVersion](https://img.shields.io/badge/targetSdkVersion-34-orange)](https://developer.android.com/distribute/best-practices/develop/target-sdk)
<br/>

![graphic image](https://github.com/easyhooon/daangn-assignment/assets/51016231/f55bc26d-3208-42db-bd4c-0df3e863bccc)

## GalleryApp

|Light Mode 1|Light Mode 2|
|:-----:|:-----:|
|<img width="240" src="https://github.com/easyhooon/daangn-assignment/assets/51016231/dfeb422c-5b83-4710-81aa-3f9ee6939ead">|<img width="240" src="https://github.com/easyhooon/daangn-assignment/assets/51016231/97b3afc6-2952-410b-96ea-b0885d66431d">|

|Dark Mode 1|Dark Mode 2|
|:-----:|:-----:|
|<img width="240" src="https://github.com/easyhooon/daangn-assignment/assets/51016231/210d9da8-f312-43e3-a844-577a401f1feb">|<img width="240" src="https://github.com/easyhooon/daangn-assignment/assets/51016231/f27f7282-36bf-4fde-b46e-f359533eff60">|

## Features

|피드 화면|Author로 사진 검색 기능|
|:-----:|:-----:|
|<img width="240" src="https://github.com/easyhooon/daangn-assignment/assets/51016231/ae4144e6-59a1-41e3-988d-5a690d5648d4">|<img width="240" src="https://github.com/easyhooon/daangn-assignment/assets/51016231/d2ab33d6-ba15-4739-baea-1b38d8de4b26">|

|상세 화면|Zoom 기능|사진 저장 기능|
|:-----:|:-----:|:-----:|
|<img width="240" src="https://github.com/easyhooon/daangn-assignment/assets/51016231/02f77c7c-4497-4e40-b39a-975cc73a9205">|<img width="240" src="https://github.com/easyhooon/daangn-assignment/assets/51016231/e38cabc4-eeaa-4b93-9663-337ca60ed596">|<img width="240" src="https://github.com/easyhooon/daangn-assignment/assets/51016231/3fbf14d8-fea5-4002-8d3c-2163a36a1119">

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
