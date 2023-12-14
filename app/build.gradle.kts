@file:Suppress("UnstableApiUsage", "INLINE_FROM_HIGHER_PLATFORM")

plugins {
    gallery("android-application")
    gallery("android-hilt")
}

android {
    namespace = "com.kenshi.gallery"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    coreLibraryDesugaring(libs.desugar.jdk)
    implementations(
        projects.data,
        projects.domain,
        projects.presentation,
        libs.androidx.core,
        libs.androidx.startup,
        libs.timber,
    )
}
