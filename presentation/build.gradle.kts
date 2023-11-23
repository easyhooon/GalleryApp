@file:Suppress("UnstableApiUsage", "INLINE_FROM_HIGHER_PLATFORM")

plugins {
    daangn("android-library")
    daangn("android-compose")
    daangn("android-hilt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.daangn.leejihun.gallery.presentation"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementations(
        projects.domain,
        libs.android.material,
        libs.androidx.activity.ktx,
        libs.androidx.appcompat,
        libs.androidx.core,
        libs.androidx.splash,
        libs.androidx.paging.runtime,
        libs.androidx.paging.compose,
        libs.timber,
        libs.bundles.androidx.compose,
        libs.bundles.androidx.lifecycle,
        libs.bundles.coil,
    )
}
