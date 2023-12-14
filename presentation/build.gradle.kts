@file:Suppress("UnstableApiUsage", "INLINE_FROM_HIGHER_PLATFORM")

plugins {
    gallery("android-library")
    gallery("android-compose")
    gallery("android-hilt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.kenshi.gallery.presentation"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementations(
        projects.domain,
        libs.kotlinx.collections.immutable,
        libs.android.material,
        libs.androidx.activity.ktx,
        libs.androidx.appcompat,
        libs.androidx.core,
        libs.androidx.splash,
        libs.androidx.paging.runtime,
        libs.androidx.paging.compose,
        libs.timber,
        libs.zoomable,
        libs.lazy.column.scrollbar,
        libs.bundles.androidx.compose,
        libs.bundles.androidx.lifecycle,
        libs.bundles.coil,
    )
}
