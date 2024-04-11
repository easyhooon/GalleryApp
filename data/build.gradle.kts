@file:Suppress("UnstableApiUsage", "INLINE_FROM_HIGHER_PLATFORM", "DSL_SCOPE_VIOLATION")

plugins {
    gallery("android-library")
    gallery("android-hilt")
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.kenshi.gallery.data"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    coreLibraryDesugaring(libs.desugar.jdk)
    implementations(
        projects.domain,
        libs.kotlinx.serialization.json,
        libs.androidx.paging.runtime,
        libs.timber,
        libs.bundles.retrofit,
        libs.bundles.okhttp,
    )
}
