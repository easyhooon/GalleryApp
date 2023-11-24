@file:Suppress("UnstableApiUsage", "INLINE_FROM_HIGHER_PLATFORM", "DSL_SCOPE_VIOLATION")

plugins {
    daangn("android-library")
    daangn("android-hilt")
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.daangn.leejihun.gallery.data"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementations(
        projects.domain,
        libs.kotlinx.serialization.json,
        libs.androidx.paging.runtime,
        libs.timber,
        libs.bundles.retrofit,
        libs.bundles.okhttp,
    )
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs +
            listOf("-opt-in=kotlin.ExperimentalStdlibApi")
    }
}
