@file:Suppress("UnstableApiUsage", "INLINE_FROM_HIGHER_PLATFORM")

plugins {
    daangn("android-application")
    daangn("android-hilt")
}

android {
    namespace = "com.daangn.leejihun.gallery"

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
