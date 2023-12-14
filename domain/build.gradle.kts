@file:Suppress("UnstableApiUsage", "INLINE_FROM_HIGHER_PLATFORM")

plugins {
    gallery("jvm-kotlin")
}

dependencies {
    implementations(
        libs.androidx.paging.common,
        libs.javax.inject,
    )
}
